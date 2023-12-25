package com.plf.boot.unified.blacklist;

import cn.hutool.json.JSONUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.plf.boot.unified.common.exception.RequestForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Aspect
@Component
@Slf4j
@Order(1)
public class BlackListAspect {

    @Resource(name="blackListCache")
    Cache<String,CountObject> blackListCache;

    @Resource(name = "countObjectCache")
    Cache<String,CountObject> countObjectCache;

    @Around("@annotation(com.plf.boot.unified.blacklist.BlackList)")
    public synchronized Object blackList(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("执行黑名单限制");
        String method = joinPoint.getTarget().getClass().getName()+"."+
                joinPoint.getSignature().getName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();

        String key = method + "-" +ip;

        log.info("进入黑名单判断方法 : {}",key);
        CountObject blackObject =  blackListCache.getIfPresent(key);

        if(blackObject != null){
            throw new RequestForbiddenException("Block by blackList : "+ JSONUtil.toJsonStr(blackObject));
        }

        int second = LocalDateTime.now().get(ChronoField.SECOND_OF_DAY);
        CountObject countObject = countObjectCache.getIfPresent(key);
        try{
            if(countObject == null){
                countObject = new CountObject(method,ip,second,1);
            }
            //不在同一秒重新计数
            if(Math.abs(countObject.getSecond() - second) >= 1){
                countObject = new CountObject(method,ip,second,1);
            }else {
                //同一秒内校验访问数量
                Integer count = countObject.increase();
                if(count == 5){
                    blackListCache.put(countObject.getKey(),countObject);
                    log.warn("{}已被加入黑名单，锁定60秒",countObject);
                    throw new RequestForbiddenException("Blocked by blackList");
                }
            }
        }finally {
            assert countObject != null;
            countObjectCache.put(countObject.getKey(),countObject);
        }
        return joinPoint.proceed();
    }
}
