package com.plf.boot.transaction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plf.boot.transaction.bean.CityInfo;
import com.plf.boot.transaction.bean.CityLog;
import com.plf.boot.transaction.mapper.CityInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class CityInfoService {

    @Resource
    private CityInfoMapper cityInfoMapper;

    @Resource
    public CityLogService cityLogService;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 实现事务自动回滚
     * @param cityInfo
     * @return
     * @throws JsonProcessingException
     */
    //配置了全局事务可以不用该注解
    //@Transactional(rollbackFor = Exception.class) //如果注释事务失效
    public boolean insert(CityInfo cityInfo) throws JsonProcessingException {
        try {
            // 插入cityInfo
            Long n = cityInfoMapper.insert(cityInfo);

            // 插入cityInfo
            if (n > 0) {
                cityLogService.insert(new CityLog("CityInfoManualService.insert", objectMapper.writeValueAsString(cityInfo)));

                // 构造异常是否会事务回滚
                throw new RuntimeException("自定义认为构造异常");
            }
            return true;
        }catch (Exception e){
            log.error("Exception message info:{}",e.getMessage());

            //需要在catch中重新抛出异常，不然事务还是不会回滚
            throw new RuntimeException(e.getMessage());
        }
    }
}
