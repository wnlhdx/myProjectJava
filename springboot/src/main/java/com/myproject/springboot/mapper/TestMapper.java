package com.myproject.springboot.mapper;

import com.myproject.springboot.entity.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
	int addUser(String userName, String password);
	int delUser(String userName);
	int setPass(String userName, String password);
	TestEntity login(String userName, String password);
}