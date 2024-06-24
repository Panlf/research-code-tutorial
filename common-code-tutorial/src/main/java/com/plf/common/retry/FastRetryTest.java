package com.plf.common.retry;

import com.burukeyou.retry.core.*;
import com.burukeyou.retry.core.task.RetryTask;

import java.util.concurrent.*;

/**
 * @author panlf
 * @date 2024/6/24
 */
public class FastRetryTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        RetryQueue queue = new FastRetryQueue(executorService);
        RetryTask<String> task = new RetryTask<String>() {
            int result = 0 ;
            @Override
            public long waitRetryTime() {
                return 2000;
            }

            @Override
            public boolean retry() {
                System.out.println("重试一次");
                return ++result < 5;
            }

            @Override
            public String getResult() {
                return result + "";
            }
        };
        CompletableFuture<String> future = queue.submit(task);
        System.out.println("任务结束 结果:"+future.get());
    }
}
