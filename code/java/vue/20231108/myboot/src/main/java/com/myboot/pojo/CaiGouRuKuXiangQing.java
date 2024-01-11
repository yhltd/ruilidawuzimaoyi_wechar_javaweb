package com.myboot.pojo;


import lombok.Data;

@Data
public class CaiGouRuKuXiangQing {
    //采购入库详情-商品
    private Integer id;
    private String shangpinBianma;
    private String name;
    private String guige;
    private String caizhi;
    private String jishuBiaozhun;
    private String zhibaoDengji;
    private String danwei;
    private String shuliang;
    private String caigouDanjia;
    private String jiashuiXiaoji;
    private String beizhu;
    private String rukuId;

    //采购入库详情-收款
    private String shouzhiBianhao;
    private String shouzhiRiqi;
    private String money;

    //采购入库详情-开票
    private String caigouBianhao;
    private String kaipiaoRiqi;
    private String jiashuiHeji;

    //采购入库详情-订金
    private String dingjin;

    //采购入库详情-订金已用
    private String yiyong;

    //采购入库详情-需开票
    private String bianhao;
    private String riqi;
    private String gongyingshang;
    private String dianpu;
    private String cangku;
    private String caigouId;
    private String rukuDanwei;
    private String yewuyuan;
    private String weikai;
    private String thisKai;

    private String shoufuType;
}
