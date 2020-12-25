package com.plf.boot.plugin.mapper;

import com.plf.boot.plugin.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author panlf
 * @date 2020/12/25
 */
@Mapper
public interface OrderMapper {
    @Select("select * from c_order where id = #{id}")
    public Order findById(@Param("id") long id);
}
