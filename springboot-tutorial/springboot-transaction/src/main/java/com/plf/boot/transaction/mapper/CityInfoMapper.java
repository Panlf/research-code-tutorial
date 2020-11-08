package com.plf.boot.transaction.mapper;

import com.plf.boot.transaction.bean.CityInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityInfoMapper {

    // 成功返回1
    @Insert("insert into city_info(province,name) values(#{province},#{name})")
    public Long insert(CityInfo cityInfo);
}
