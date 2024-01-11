package com.myboot.pojo;


import lombok.Data;

@Data
public class CaiGouDingDanXiangQing {
    //采购订单详情-商品
    private Integer id;
    private String shangpinBianma;
    private String name;
    private String guige;
    private String caizhi;
    private String jishuBiaozhun;
    private String zhibaoDengji;
    private String danwei;
    private String shuliang;
    private String rukuShuliang;
    private String weichuShuliang;
    private String caigouDanjia;
    private String jiashuiXiaoji;

    //采购订单详情-入库单
    private String bianhao;
    private String riqi;
    private String beizhu;

    //采购订单详情-收款
    private String shouzhiBianhao;
    private String shouzhiRiqi;
    private String shoufuType;
    private String money;

    //采购订单-开票
    private String caigouBianhao;
    private String kaipiaoRiqi;
    private String jiashuiHeji;

    //采购订单-需收票
    private String gongyingshang;
    private String dianpu;
    private String cangku;
    private String caigouId;
    private String rukuDanwei;
    private String yewuyuan;
    private String rukuId;
    private String weikai;
    private String thisKai;

}
