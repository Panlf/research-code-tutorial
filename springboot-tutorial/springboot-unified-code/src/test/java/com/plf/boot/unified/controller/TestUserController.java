package com.plf.boot.unified.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("用户控制器测试")
public class TestUserController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testController() throws Exception {
        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/unified/get")
                .param("id","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse());
    }


}
