package com.myboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class XiaoShouChuKuAdd {
    private Integer id;
    private String bianhao;
    private String riqi;
    private String kehu;
    private String dianpu;
    private String cangku;
    private String beizhu;
    private String xiaoshouId;
    private List<XiaoShouChuKuItem> chuKuItem;
    private String jiashuiXiaoji;
    private String yifu;
    private String weifu;
    private String isselect;

    private String chukuDanwei;
    private String yewuyuan;
}
