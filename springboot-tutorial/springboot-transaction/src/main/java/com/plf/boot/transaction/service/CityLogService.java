package com.plf.boot.transaction.service;

import com.plf.boot.transaction.bean.CityLog;
import com.plf.boot.transaction.mapper.CityLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CityLogService {

    @Resource
    private CityLogMapper cityLogMapper;

    @Transactional(rollbackFor = Exception.class)
    public Long insert(CityLog cityLog){
        return  cityLogMapper.insert(cityLog);
    }
}
