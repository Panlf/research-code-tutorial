package com.plf.boot.strategy.test;

import com.plf.boot.strategy.handler.RootHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author panlf
 * @date 2022/7/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HandlerTest {

    @Autowired
    RootHandler rootHandler;

    @Test
   public void testHandler(){
        System.out.println(rootHandler.applyStrategy(6));
    }
}
