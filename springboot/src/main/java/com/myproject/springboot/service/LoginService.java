package com.myproject.springboot.service;

import com.myproject.springboot.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.springboot.mapper.LoginMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 31446
 */
@Service
public class LoginService implements UserDetailsService {
	@Autowired
    LoginMapper loginMapper;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String addUser(String userName, String password){
        password=passwordEncoder().encode(password);
        if(loginMapper.addUser(userName,password)>0) {
        	return "注册成功";
        }else {
        	return "注册失败";
        }
        
    }

    public String delUser(String userName){
    	 if(loginMapper.delUser(userName)>0) {
         	return "删除成功";
         }else {
         	return "删除失败";
         }
    }
    
    public String setPass(String userName, String password){
    	if(loginMapper.setPass(userName,password)>0) {
         	return "设置成功";
         }else {
         	return "设置失败";
         }
    }
    
    public String login(String userName, String password){
    	LoginEntity user = loginMapper.queryUser(userName);
    	if(user!=null) {
         	return user.getUserName();
         }else {
        	return "登陆失败";
         }
    }

    public String changeToAdmin(String userName){
        if(loginMapper.changeToAdmin(userName)>0) {
            return "已提升为管理员，提升成功";
        }else {
            return "已经是管理员，提升失败";
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginEntity user = loginMapper.queryUser(username);
        Collection<GrantedAuthority> authList = getAuthorities();
        return user;
    }

    private Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("0"));
        authList.add(new SimpleGrantedAuthority("1"));
        return authList;
    }
}