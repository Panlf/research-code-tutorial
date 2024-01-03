package com.plf.resilience4j.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@Slf4j
public class Resilience4jController {

    @GetMapping("retry")
    @Retry(name="retryApi",fallbackMethod="fallback")
    public ResponseEntity<String> retryApi(){
        log.info("request retryApi");
        new RestTemplate().getForEntity("http://localhost:9090/getUser",String.class);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }


    @GetMapping("circuitBreaker")
    @CircuitBreaker(name = "circuitBreakerApi",fallbackMethod = "fallback")
    public ResponseEntity<String> circuitBreakerApi(){
        log.info("request circuitBreakerApi");

        int r = new Random().nextInt(100);

        if(r >= 70){
            throw new RuntimeException("circuitBreakerApi 访问出现异常");
        }

        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @GetMapping("ratelimit")
    @RateLimiter(name="ratelimitApi",fallbackMethod = "fallback")
    public ResponseEntity<String> ratelimitApi(){
        log.info("request ratelimitApi");
        return new ResponseEntity<>("success",HttpStatus.OK);
    }


    public ResponseEntity<String> fallback(Throwable e){
        log.error("fallback exception , {}",e.getMessage());
        return new ResponseEntity<>("您请求过于频繁，稍后再试",HttpStatus.OK);
    }

}
