package com.plf.common.retry;

import com.github.rholder.retry.*;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author panlf
 * @date 2022/7/9
 */
public class GuavaRetryTest {
    static Random random = new Random();
    public static void main(String[] args) {
      /* Optional<Integer> result = RetryUtils.fromTask(
               RetryTest::getCount
        ).retryTimes(5).getResult();
        System.out.println(result.get());*/

        Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
                .retryIfExceptionOfType(AccessException.class)
                /**
                 * retryIfException： retryIfException，抛出 runtime 异常、checked 异常时都会重试，但是抛出 error 不会重试。
                 * retryIfRuntimeException： retryIfRuntimeException 只会在抛 runtime 异常的时候才重试，checked 异常和error 都不重试。
                 * retryIfExceptionOfType： retryIfExceptionOfType 允许我们只在发生特定异常的时候才重试，比如NullPointerException 和 IllegalStateException 都属于 runtime 异常，也包括自定义的error。
                 */
                //.retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.incrementingWait(2, TimeUnit.SECONDS, 2, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(6))
                .build();
        try {
           Integer result= retryer.call(GuavaRetryTest::getCount);
            System.out.println(result);
        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }
    }
    public static Integer getCount() throws AccessException {
       int r = random.nextInt(10) ;
       if(r<=5){
            System.out.println("请求数据失败");
            //throw new RuntimeException("不符合要求");
           throw new AccessException("没权限啊");
        }
        return r;
    }
}
