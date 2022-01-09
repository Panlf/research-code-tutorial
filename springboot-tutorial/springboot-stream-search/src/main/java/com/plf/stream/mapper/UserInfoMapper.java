package com.plf.stream.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plf.stream.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

/**
 * @author panlf
 * @date 2022/1/8
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("${sql}")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 1000)
    @ResultType(UserInfo.class)
    void fetchUserInfo(@Param("sql") String sql, ResultHandler<UserInfo> handler);

    @Select("select id,age,name,createtime,price,address from user_info")
    List<UserInfo> findAllUserInfo();
}
