package com.plf.common.retry;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author panlf
 * @date 2022/7/9
 */
public class RetryUtils<R> {
    // 重试等待时间长度
    private long retryTimesLong = 500L;
    // 重试次数
    private int retryTimes = 3;
    //返回结果
    private R result;
    //重试业务逻辑
    private Supplier<R> supplier;

    public RetryUtils<R> supplier(Supplier<R> supplier){
        this.supplier = supplier;
        return this;
    }

    public RetryUtils<R> retryTimes(int retryTimes){
        this.retryTimes = retryTimes;
        return this;
    }

    public RetryUtils<R> retryTimesLong(long retryTimesLong){
        this.retryTimesLong = retryTimesLong;
        return this;
    }

    public static <T> RetryUtils <T> fromTask(Supplier<T> supplier){
        RetryUtils<T> retryUtils = new RetryUtils<>();
        retryUtils.supplier(supplier);
        return retryUtils;
    }

    public Optional<R> getResult(){
        int currentRetryTime = 1;
        boolean retryIsSuccess = Boolean.FALSE;
        while(currentRetryTime < retryTimes + 1){
            System.out.println("第"+currentRetryTime+"次请求数据");
            currentRetryTime++;
            try {
                result = this.supplier.get();

                retryIsSuccess = Boolean.TRUE;
            } catch (Exception e){
                if((!retryIsSuccess) && currentRetryTime < this.retryTimes){

                    try {
                        Thread.sleep(this.retryTimesLong);
                    } catch (InterruptedException ex){
                        throw new RuntimeException(ex);
                    }

                    continue;
                }

                throw new RuntimeException(e);
            }

            if(retryIsSuccess){
                return  Optional.ofNullable(result);
            }
        }
        return Optional.empty();
    }

}
