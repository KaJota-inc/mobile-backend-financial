package com.example.springwithPostgreSql.service;

import com.example.springwithPostgreSql.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUser();

    UserEntity getUserById(Long userId);

    void saveUser(UserEntity user);

    void updateUser(UserEntity user, Long userid);

    void deleteUserbyId(Long userId);
}
