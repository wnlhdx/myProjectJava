package com.myproject.springboot.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.springboot.service.TestService;

@CrossOrigin
@RestController
public class TestController {
	 @Autowired
	 private TestService testService;
	 
	 @RequestMapping("/AddUser")
	 @PreAuthorize("hasAuthority('1')")
	 public String addUser(@RequestParam(value="username") String userName, @RequestParam(value="password")String password){
	        return testService.addUser(userName,password);
	 }
	 
	 @RequestMapping("/DelUser")
	 @PreAuthorize("hasAuthority('1')")
	 public String delUser(@RequestParam(value="username") String userName){
	        return testService.delUser(userName).toString();
	 }
	 
	 @RequestMapping("/SetPass")
	 @PreAuthorize("hasAuthority('1')")
	 public String setPass(@RequestParam(value="username") String userName, @RequestParam(value="password")String password){
	        return testService.setPass(userName,password);
	 }

	@RequestMapping("/ChangeToAdmin")
	@PreAuthorize("hasAuthority('1')")
	public String changeToAdmin(@RequestParam(value="username") String userName){
		return testService.changeToAdmin(userName);
	}
	 
	 @RequestMapping("/Login")
	 public String login(@RequestParam(value="username") String userName, @RequestParam(value="password")String password){
	        return testService.login(userName,password);
	 }
	 
	 
	 
	 
}