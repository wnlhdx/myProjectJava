package com.myproject.springboot;

import com.myproject.springboot.controller.LoginController;
import com.myproject.springboot.entity.LoginEntity;
import com.myproject.springboot.mapper.LoginMapper;
import com.myproject.springboot.service.LoginService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;


/**
 * @author lkxl
 */
@SpringBootTest
public class AplicationTest {
    @InjectMocks
    private LoginController mockitoController;
    @InjectMocks
    private LoginService mockitoService;
    @Mock
    private LoginMapper mockitoDao;
    @Test
    public void testLoginService(){
        LoginEntity loginEntity =new LoginEntity();
        loginEntity.setUserName("wnlhdx");
        loginEntity.setIsAdmin(1);
        loginEntity.setPassWord("w1995520");
        Mockito.when(mockitoDao.queryUser("wnlhdx")).thenReturn(loginEntity);
        ReflectionTestUtils.setField(mockitoController, "testService", mockitoService);
        String res=mockitoController.login("wnlhdx","w1995520");
        assertEquals(res,"wnlhdx");
        
    }
}
