package com.plf.nacos.controller;

import com.plf.nacos.config.DynamicThreadPool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2023/5/15
 */
@RestController
@RequestMapping("/threadpool")
public class ThreadPoolController {

    @Resource
    private DynamicThreadPool dynamicThreadPool;

    /**
     * 打印当前线程池的状态
     */
    @GetMapping("/print")
    public String printThreadPoolStatus() {
        return dynamicThreadPool.printThreadPoolStatus();
    }

    /**
     * 给线程池增加任务
     *
     * @param count
     */
    @GetMapping("/add")
    public String dynamicThreadPoolAddTask(int count) {
        dynamicThreadPool.dynamicThreadPoolAddTask(count);
        return String.valueOf(count);
    }
}
