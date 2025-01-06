package com.myproject.user.service;

import com.myproject.user.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserEntity user);
    UserEntity getUserByUsername(String username);
}
