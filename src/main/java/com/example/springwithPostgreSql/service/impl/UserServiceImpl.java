package com.example.springwithPostgreSql.service.impl;

import com.example.springwithPostgreSql.entity.UserEntity;
import com.example.springwithPostgreSql.repository.UserRepository;
import com.example.springwithPostgreSql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent())
            return userOpt.get();
        else
            throw new RuntimeException("user not found.");
    }

    @Override
    public void saveUser(UserEntity user) {
        String password = user.getPassword();
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypedPassword = cipher.doFinal(password.getBytes());
            user.setPassword(Arrays.toString(encrypedPassword));
        }catch(
                NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException |
                IllegalBlockSizeException e
        ){
            throw new RuntimeException(e.getMessage());
        }
        UserEntity userDetail = userRepository.save(user);
        System.out.println("user saved to db with userId : " + userDetail.getId());
    }

    @Override
    public void updateUser(UserEntity user, Long userId) {
        Optional<UserEntity> userDetailOpt = userRepository.findById(userId);
        if(userDetailOpt.isPresent()){
            UserEntity userDetail = userDetailOpt.get();
            if(user.getName() != null || user.getName().isEmpty())
                userDetail.setName(user.getName());
            if(user.getPassword() != null || user.getPassword().isEmpty())
                userDetail.setPassword(user.getPassword());
            if(user.getCity() != null || user.getCity().isEmpty())
                userDetail.setCity(user.getCity());
            userRepository.save(userDetail);
        }else{
            throw new RuntimeException("user not found.");
        }
    }

    @Override
    public void deleteUserbyId(Long userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent())
            userRepository.deleteById(userId);
        else
            throw new RuntimeException("user not found.");
    }
}
