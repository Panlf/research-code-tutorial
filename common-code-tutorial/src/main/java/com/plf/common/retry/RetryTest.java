package com.plf.common.retry;

import com.github.rholder.retry.*;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author panlf
 * @date 2022/7/9
 */
public class RetryTest {
    static  Random random = new Random();
    public static void main(String[] args) {
      /* Optional<Integer> result = RetryUtils.fromTask(
               RetryTest::getCount
        ).retryTimes(5).getResult();
        System.out.println(result.get());*/

        Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
                .retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.incrementingWait(2, TimeUnit.SECONDS, 2, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(6))
                .build();
        try {
           Integer result= retryer.call(RetryTest::getCount);
            System.out.println(result);
        } catch (ExecutionException | RetryException e) {
            e.printStackTrace();
        }
    }

    public static Integer getCount(){
       int r = random.nextInt(10) ;
       if(r<=5){
           System.out.println("请求数据失败");
            throw new RuntimeException("不符合要求");
        }
        return r;
    }
}
