package com.myboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class GongYingShangAdd {
    private Integer id;
    private String bianhao;
    private String name;
    private String gongyingshangDengji;
    private String suozaiDiqu;
    private String dizhi;
    private String beizhu;
    private String caigouyuan;
    private String jianzuoKehu;
    private String zhuyaoYewu;
    private String shoujianName;
    private String shoujianPhone;
    private String shoujianDizhi;
    private String kaipiaoDanwei;
    private String shibiehao;
    private String kaipiaoDizhi;
    private String kaipiaoDianhua;
    private String kaipiaoYinhang;
    private String kaipiaoZhanghao;
    private List<GongYingShangItem> itemList;
}
