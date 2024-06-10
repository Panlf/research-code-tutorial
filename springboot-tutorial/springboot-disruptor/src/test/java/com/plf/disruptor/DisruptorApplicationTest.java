package com.plf.disruptor;

import com.plf.disruptor.service.DisruptorMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2023/1/19
 */
@Slf4j
@SpringBootTest(classes = DisruptorApplication.class)
public class DisruptorApplicationTest {

    @Resource
    private DisruptorMessageService disruptorMessageService;

    @Test
    public void messageTest() throws InterruptedException {
        disruptorMessageService.createMessage("Hello Disruptor Message");
        log.info("消息队列已发送完毕");
        //异步处理消息
        Thread.sleep(2000);
    }
}
