package com.myboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ShouZhiMingXiAdd {
    private Integer id;
    private String dianpu;
    private String danjuLeixing;
    private String danjuBianhao;
    private String shouzhiBianhao;
    private String shouzhiRiqi;
    private String jizhangren;
    private String jizhangType;
    private String jizhangZhanghu;
    private String jizhangJine;
    private String kediShuie;
    private String zhaiyao;
    private String shouzhiType;
    private List<ShouZhiMingXiItem> shouzhiItem;

    private String jizhangDanwei;
    private String shoufuType;
}
