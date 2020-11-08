package com.plf.boot.transaction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plf.boot.transaction.bean.CityInfo;
import com.plf.boot.transaction.bean.CityLog;
import com.plf.boot.transaction.mapper.CityInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;

/**
 * 手动事务
 *   手动提交、手动回滚
 */
@Service
@Slf4j
public class CityInfoManualService {

    @Resource
    private CityInfoMapper cityInfoMapper;

    @Resource
    private CityLogManualService cityLogManualService;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Resource
    private TransactionDefinition transactionDefinition;

    public boolean insert(CityInfo cityInfo) throws JsonProcessingException {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            // 插入cityInfo
            Long n = cityInfoMapper.insert(cityInfo);

            // 插入cityInfo
            if (n > 0) {
                cityLogManualService.insert(new CityLog("CityInfoManualService.insert", objectMapper.writeValueAsString(cityInfo)));

                // 构造异常是否会事务回滚
                throw new RuntimeException("自定义认为构造异常");
            }
            dataSourceTransactionManager.commit(transactionStatus);
            return true;
        }catch (Exception e){
           log.error("Exception message info:{}",e.getMessage());
           dataSourceTransactionManager.rollback(transactionStatus);
           return false;
        }
    }
}
