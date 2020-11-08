package com.plf.boot.transaction.service;

import com.plf.boot.transaction.bean.CityLog;
import com.plf.boot.transaction.mapper.CityLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CityLogManualService {

    @Resource
    private CityLogMapper cityLogMapper;

    public Long insert(CityLog cityLog){
        return  cityLogMapper.insert(cityLog);
    }
}
