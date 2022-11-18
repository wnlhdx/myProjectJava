package com.myproject.service;

import com.myproject.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mapper.TestMapper;

/**
 * @author 31446
 */
@Service
public class TestService {
	@Autowired
    TestMapper testMapper;
    public String addUser(String userName, String password){
        if(testMapper.addUser(userName,password)>0) {
        	return "注册成功";
        }else {
        	return "注册失败";
        }
        
    }

    public String delUser(String userName){
    	 if(testMapper.delUser(userName)>0) {
         	return "删除成功";
         }else {
         	return "删除失败";
         }
    }
    
    public String setPass(String userName, String password){
    	if(testMapper.setPass(userName,password)>0) {
         	return "设置成功";
         }else {
         	return "设置失败";
         }
    }
    
    public String login(String userName, String password){
    	TestEntity user =testMapper.login(userName,password);
    	if(user!=null) {
         	return user.getUserName();
         }else {
        	return "登陆失败";
         }
    }
}