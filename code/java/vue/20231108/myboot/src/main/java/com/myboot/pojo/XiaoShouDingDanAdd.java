package com.myboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class XiaoShouDingDanAdd {
    private Integer id;
    private String bianhao;
    private String riqi;
    private String kehu;
    private String yewuyuan;
    private String shoujianren;
    private String shoujianPhone;
    private String shoujianDizhi;
    private String dianpu;
    private String xiaoxiangShuilv;
    private String beizhu;
    private String shenhe;
    private String jiashuiHeji;
    private String jiageDengji;
    private String shenheZhuangtai;
    private String xiaoshouDanwei;
    private String shenheList;
    private List<XiaoShouDingDanItem> itemList;
}
