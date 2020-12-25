package com.plf.boot.plugin.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;
    private Date createTime;
    private Double price;
    private Integer userId;
}
