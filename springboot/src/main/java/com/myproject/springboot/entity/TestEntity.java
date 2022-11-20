package com.myproject.springboot.entity;

import org.springframework.stereotype.Component;

@Component
public class TestEntity {
	private String userName;
    private String password;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassWord(String passWord) {
		this.password = passWord;
	}
    
    @Override
	public String toString() {
		return "TestEntity [userName=" + userName + ", password=" + password + "]";
	}
}