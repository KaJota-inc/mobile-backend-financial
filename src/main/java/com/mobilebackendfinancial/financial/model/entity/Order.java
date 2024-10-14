package com.mobilebackendfinancial.financial.model.entity;

import com.mobilebackendfinancial.financial.constant.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name ="user_id", nullable = false)
    private String userId;

    @Column(name = "order_status", nullable = false)
    private OrderStatus status;

    @Column(name = "currency", length = 3, nullable = false)
    private String ccy; // Currency code, e.g., USD

    @Column(name = "price", nullable = false)
    @PositiveOrZero(message = "price cannot be negative")
    private BigDecimal price;

//    @Column(name = "reference", nullable = false)
//    private String reference;

    @Column(name="order_status_change_time", nullable = false)
    private Instant orderStatusChangeTime;

    @Column(name="order_rating", nullable = false)
    @PositiveOrZero(message = "Rating cannot be negative")
    private Double orderRating;

    @Column(name="shippingAddress", nullable = false)
    private String shippingAddress;

    @Column(name="recipient_phonenumber", nullable = false)
    private String recipientPhoneNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orderItem;

    @CreationTimestamp
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @CreationTimestamp
    @Column(name = "order_time", nullable = false)
    private LocalTime orderTime;

}
