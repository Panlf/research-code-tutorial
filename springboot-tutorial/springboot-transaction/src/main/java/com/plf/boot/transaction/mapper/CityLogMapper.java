package com.plf.boot.transaction.mapper;

import com.plf.boot.transaction.bean.CityLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityLogMapper {

    @Insert("insert into city_log(method_name,data) values(#{methodName},#{data})")
    public Long insert(CityLog cityLog);
}
