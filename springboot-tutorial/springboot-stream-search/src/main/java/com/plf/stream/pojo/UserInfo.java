package com.plf.stream.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author panlf
 * @date 2022/1/8
 */
@Data
public class UserInfo {

    private Integer id;
    private Integer age;
    private String name;
    private Date createtime;
    private Double price;
    private String address;
}
