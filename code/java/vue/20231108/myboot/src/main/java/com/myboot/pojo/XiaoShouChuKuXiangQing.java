package com.myboot.pojo;


import lombok.Data;

@Data
public class XiaoShouChuKuXiangQing {
    //销售出库详情-商品
    private Integer id;
    private String shangpinBianma;
    private String name;
    private String guige;
    private String caizhi;
    private String jishuBiaozhun;
    private String zhibaoDengji;
    private String danwei;
    private String shuliang;
    private String xiaoshouDanjia;
    private String jiashuiXiaoji;
    private String beizhu;
    private String chukuId;

    //销售出库详情-收款
    private String shouzhiBianhao;
    private String shouzhiRiqi;
    private String money;

    //销售出库详情-开票
    private String xiaoshouBianhao;
    private String kaipiaoRiqi;
    private String jiashuiHeji;

    //销售出库详情-订金
    private String dingjin;

    //销售出库详情-订金已用
    private String yiyong;

    //销售出库详情-需开票
    private String bianhao;
    private String riqi;
    private String kehu;
    private String dianpu;
    private String cangku;
    private String xiaoshouId;
    private String chukuDanwei;
    private String yewuyuan;
    private String weikai;
    private String thisKai;

}
