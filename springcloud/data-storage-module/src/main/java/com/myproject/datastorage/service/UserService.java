package com.myproject.datastorage.service;

import com.myproject.datastorage.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserByUsername(String username);
    void addUser(UserEntity user);
    void deleteUser(Long id);
}
