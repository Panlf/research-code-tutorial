package com.plf.boot.unified.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author panlf
 * @date 2022/5/10
 */
@Component
@Slf4j
public class TestSchedule {

    @Scheduled(cron = "0/10 * * * * ?")
    public void beatHeart(){
       log.info("TestSchedule beat heart ... ");
    }

}
