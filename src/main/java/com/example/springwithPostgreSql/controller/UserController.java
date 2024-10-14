package com.example.springwithPostgreSql.controller;


import com.example.springwithPostgreSql.entity.UserEntity;
import com.example.springwithPostgreSql.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public List<UserEntity> getAllUser(){
        return userServiceImpl.getAllUser();
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId){
        return userServiceImpl.getUserById(userId);
    }

    @PostMapping("/save-user")
    public String saveUser(@RequestBody UserEntity user){
        userServiceImpl.saveUser(user);
        return "user saved successfuly.";
    }

    @PutMapping("/update/{userId}")
    public String updateUser(@RequestBody UserEntity user, @PathVariable Long userId){
        userServiceImpl.updateUser(user, userId);
        return "user updated successfully.";
    }
    @DeleteMapping("/delete/{userId}")
    public String deleteUseryId(@PathVariable Long userId){
        userServiceImpl.deleteUserbyId(userId);
        return "user deleted successfully.";
    }

}
