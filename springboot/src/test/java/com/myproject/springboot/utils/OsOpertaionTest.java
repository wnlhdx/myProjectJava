package com.myproject.springboot.utils;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OsOpertaionTest {
	private static final Logger log= LoggerFactory.getLogger(OsOpertaionTest.class);
	@Autowired
	private OsOpertaion osOpertaion;
	@Test
	void testOsOperation() {
		
	}
}