package com.myboot.pojo;


import lombok.Data;

@Data
public class XiaoShouDingDanXiangQing {
    //销售订单详情-商品
    private Integer id;
    private String shangpinBianhao;
    private String shangpinMingcheng;
    private String guige;
    private String caizhi;
    private String jishuBiaozhun;
    private String zhibaoDengji;
    private String danwei;
    private String shuliang;
    private String chukuShuliang;
    private String weichuShuliang;
    private String baojiaDanjia;
    private String jiashuiXiaoji;

    //销售订单详情-出库单
    private String bianhao;
    private String riqi;
    private String beizhu;

    //销售订单详情-收款
    private String shouzhiBianhao;
    private String shouzhiRiqi;
    private String shoufuType;
    private String money;

    //销售订单-开票
    private String xiaoshouBianhao;
    private String kaipiaoRiqi;
    private String jiashuiHeji;

    //销售订单-需开票
    private String kehu;
    private String dianpu;
    private String cangku;
    private String xiaoshouId;
    private String chukuDanwei;
    private String yewuyuan;
    private String chukuId;
    private String weikai;
    private String thisKai;

}
