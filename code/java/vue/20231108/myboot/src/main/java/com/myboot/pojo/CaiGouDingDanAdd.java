package com.myboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CaiGouDingDanAdd {
    private Integer id;
    private String bianhao;
    private String riqi;
    private String gongyingshang;
    private String dianpu;
    private String jinxiangShuilv;
    private String beizhu;
    private String shenhe;
    private String shenheZhuangtai;
    private String rukuZhuangtai;
    private String shoupiaoZhuangtai;
    private String fukuanZhuangtai;
    private List<CaiGouDingDanItem> productList;


}
