package com.mobilebackendfinancial.financial.service;

import com.mobilebackendfinancial.financial.constant.OrderStatus;
import com.mobilebackendfinancial.financial.constant.ResponseCodes;
import com.mobilebackendfinancial.financial.exception.GenericException;
import com.mobilebackendfinancial.financial.model.dto.request.FetchOrderDto;
import com.mobilebackendfinancial.financial.model.dto.request.FetchUserOrdersDto;
import com.mobilebackendfinancial.financial.model.dto.request.PlaceOrderDto;
import com.mobilebackendfinancial.financial.model.dto.request.UpdateOrderStatusDto;
import com.mobilebackendfinancial.financial.model.entity.Order;
import com.mobilebackendfinancial.financial.model.factory.OrderFactory;
import com.mobilebackendfinancial.financial.repository.OrderItemRepository;
import com.mobilebackendfinancial.financial.repository.OrderRespository;
import com.mobilebackendfinancial.financial.service.ServiceInterfaces.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRespository orderRespository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public PlaceOrderDto.ResponseDto placeOrder(PlaceOrderDto.RequestDto requestDto) {
        try {
            //Create new order from requestDto
            Order newOrder = OrderFactory.fromDto(requestDto);
            //Save to the order table
            orderRespository.save(newOrder);
            //For all order items store the joinColumn field "Order"
            requestDto.getOrderItems().forEach(item -> item.setOrder(newOrder));
            //Save them all in the order item table
            orderItemRepository.saveAll(requestDto.getOrderItems());

            return new PlaceOrderDto.ResponseDto().setMessage("Order placed");

        } catch (Exception ex) {
            log.error("An error occurred while placing order: {}", ex.getMessage());
        }
        return null;
    }

    @Override
    public FetchUserOrdersDto.ResponseDto fetchUserOrders(FetchUserOrdersDto.RequestDto requestDto) {
        List<Order> fetchedOrders = orderRespository.findAllByUserId(requestDto.getUserId())
                .orElseThrow(() ->
                        new GenericException(
                                ResponseCodes.NOT_FOUND,
                                "No order was found",
                                HttpStatus.NOT_FOUND,
                                null
                        ));
        if (fetchedOrders.isEmpty()) {
            throw new GenericException(
                    ResponseCodes.NOT_FOUND,
                    "No order was found for user",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
        try {
            return new FetchUserOrdersDto.ResponseDto().setOrders(fetchedOrders);
        } catch (Exception ex) {
            log.info("An error occurred while trying to fetch user orders: {}", ex.getMessage());
        }
        return null;
    }

    @Override
    public FetchOrderDto.ResponseDto fetchOrder(FetchOrderDto.RequestDto requestDto) {

        log.info("The type of this requestDto order id is: {}", requestDto.getOrderId());

        Order fetchedOrder = orderRespository.findById(requestDto.getOrderId())
                .orElseThrow(() -> new GenericException(
                        ResponseCodes.NOT_FOUND,
                        "Order with id was not found",
                        HttpStatus.NOT_FOUND,
                        null
                ));

        try {
            return new FetchOrderDto.ResponseDto().setOrder(fetchedOrder);
        } catch (Exception ex) {
            log.info("something happened while trying to fetch order: {}", ex.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public UpdateOrderStatusDto.ResponseDto updateOrderStatus(UpdateOrderStatusDto.RequestDto requestDto) {

        if(requestDto.getPrevStatus() == null || requestDto.getPrevStatus().toString().trim().isEmpty()){
            throw new GenericException(
                    ResponseCodes.INVALID_CREDENTIAL,
                    "Previous Status cannot be empty",
                    HttpStatus.BAD_REQUEST,
                    null
            );
        }
        Order fetchedOrder = orderRespository.findById(requestDto.getOrderId())
                .orElseThrow(()-> new GenericException(
                        ResponseCodes.NOT_FOUND,
                        "Order with id was not found",
                        HttpStatus.NOT_FOUND,
                        null
                ));
        switch (requestDto.getPrevStatus()){
            case COLLECTED:
                fetchedOrder.setStatus(OrderStatus.PICKEDUP);
                break;
            case PICKEDUP:
                fetchedOrder.setStatus(OrderStatus.INTRANSIT);
                break;
            case INTRANSIT:
                fetchedOrder.setStatus(OrderStatus.DELIVERED);
                break;
            default:
                throw new GenericException(
                        ResponseCodes.INVALID_CREDENTIAL,
                        "Invalid previous status",
                        HttpStatus.BAD_REQUEST,
                        null
                );
        }

        orderRespository.save(fetchedOrder);

        return new UpdateOrderStatusDto.ResponseDto().setUpdatedOrder(fetchedOrder);
    }
}
