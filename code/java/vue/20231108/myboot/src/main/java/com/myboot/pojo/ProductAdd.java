package com.myboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ProductAdd {
    private Integer id;
    private String name;
    private String type;
    private String danwei;
    private String caizhi;
    private String jishuBiaozhun;
    private String zhibaoDengji;
    private String beizhu;
    private Integer itemId;
    private String productId;
    private String guige;
    private String bianhao;
    private String lingshouPrice;
    private String lingshouBili;
    private String pifaPrice;
    private String pifaBili;
    private String dakehuPrice;
    private String dakehuBili;
    private String caigouPrice;
    private String jinxiang;
    private String xiaoxiang;
    private String enable;
    private List<ProductItem> itemList;
}
