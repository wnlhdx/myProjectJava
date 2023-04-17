package com.myproject.springboot.utils;

import com.myproject.springboot.controller.TestController;
import com.myproject.springboot.entity.TestEntity;
import com.myproject.springboot.mapper.TestMapper;
import com.myproject.springboot.service.TestService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;


/**
 * @author lkxl
 */
@SpringBootTest
public class AplicationTest {
    @InjectMocks
    private TestController mockitoController;
    @InjectMocks
    private TestService mockitoService;
    @Mock
    private TestMapper mockitoDao;
    @Test
    public void testLoginService(){
        TestEntity testEntity=new TestEntity();
        testEntity.setUserName("wnlhdx");
        testEntity.setIsAdmin(1);
        testEntity.setPassWord("w1995520");
        Mockito.when(mockitoDao.queryUser("wnlhdx")).thenReturn(testEntity);
        ReflectionTestUtils.setField(mockitoController, "testService", mockitoService);
        String res=mockitoController.login("wnlhdx","w1995520");
        assertEquals(res,"wnlhdx");
    }
}
