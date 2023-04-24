package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.LoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
	int addUser(String userName, String password);
	int delUser(String userName);
	int setPass(String userName, String password);
	int changeToAdmin(String userName);
	LoginEntity queryUser(String userName);
}