package com.myproject.springboot.utils;


import com.myproject.springboot.config.WebSecurityConfig;
import com.myproject.springboot.controller.TestController;
import com.myproject.springboot.mapper.TestMapper;
import com.myproject.springboot.service.TestService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author lkxl
 */


@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(username = "wnlhdx", password = "w1995520")
public class ServiceTest {
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }
    @Autowired
    private MockMvc mockMvc;
    private static final Logger log= LoggerFactory.getLogger(ServiceTest.class);
    @Test
    void testController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("username","wnlhdx")
                        .param("password","w1995520"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().string("Hello, spring"));
    }
}
