package com.myboot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    //商品id
    private Integer id;
    //商品名称
    private String name;
    //商品分类
    private String type;
    //单位
    private String danwie;
    //材质
    private String caizhi;
    //技术标准
    private String jishuBiaozhun;
    //备注
    private String beizhu;

    private static final long serialVersionUID = 1L;
}
