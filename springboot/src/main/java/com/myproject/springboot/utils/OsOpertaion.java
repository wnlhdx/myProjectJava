package com.myproject.springboot.utils;

import org.springframework.stereotype.Component;

/**
 * @author 31446
 */
@Component
public class OsOpertaion {
	public String checkOS(){
		return System.getProperty("os.name");
	}
}