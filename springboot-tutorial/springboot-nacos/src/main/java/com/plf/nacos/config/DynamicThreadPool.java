package com.plf.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author panlf
 * @date 2023/5/15
 */
@RefreshScope
@Configuration
public class DynamicThreadPool implements InitializingBean {
    @Value("${core.size}")
    private String coreSize;

    @Value("${max.size}")
    private String maxSize;

    private static ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private NacosConfigManager nacosConfigManager;

    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        //按照nacos配置初始化线程池
        threadPoolExecutor = new ThreadPoolExecutor(Integer.parseInt(coreSize), Integer.parseInt(maxSize), 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("c_t_%d").build(),
                (r, executor) -> System.out.println("rejected!"));

        //nacos配置变更监听
        nacosConfigManager.getConfigService().addListener("dynamic-thread.yml", nacosConfigProperties.getGroup(),
                new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        //配置变更，修改线程池配置
                        System.out.println(configInfo);
                        changeThreadPoolConfig(Integer.parseInt(coreSize), Integer.parseInt(maxSize));
                    }
                });
    }

    /**
     * 打印当前线程池的状态
     */
    public String printThreadPoolStatus() {
        return String.format("core_size:%s,thread_current_size:%s;" +
                        "thread_max_size:%s;queue_current_size:%s,total_task_count:%s", threadPoolExecutor.getCorePoolSize(),
                threadPoolExecutor.getActiveCount(), threadPoolExecutor.getMaximumPoolSize(), threadPoolExecutor.getQueue().size(),
                threadPoolExecutor.getTaskCount());
    }

    /**
     * 给线程池增加任务
     *
     * @param count
     */
    public void dynamicThreadPoolAddTask(int count) {
        for (int i = 0; i < count; i++) {
            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(finalI);
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 修改线程池核心参数
     *
     * @param coreSize
     * @param maxSize
     */
    private void changeThreadPoolConfig(int coreSize, int maxSize) {
        threadPoolExecutor.setCorePoolSize(coreSize);
        threadPoolExecutor.setMaximumPoolSize(maxSize);
    }
}
