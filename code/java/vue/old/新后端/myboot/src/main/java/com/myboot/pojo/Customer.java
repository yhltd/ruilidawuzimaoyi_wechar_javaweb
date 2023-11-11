package com.myboot.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * customer
 * @author 
 */
@Data
public class Customer implements Serializable {
    private Integer id;

    /**
     * 客户编号
     */
    private String bianhao;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户分类
     */
    private String type;

    /**
     * 上级单位
     */
    private String shangjiDanwei;

    /**
     * 客户等级
     */
    private String kehuDengji;

    /**
     * 价格等级
     */
    private String jiageDengji;

    /**
     * 所在地区
     */
    private String suozaiDiqu;

    /**
     * 地址
     */
    private String dizhi;

    /**
     * 备注
     */
    private String beizhu;

    /**
     * 业务员
     */
    private String yewuyuan;

    /**
     * 收件人姓名
     */
    private String shoujianName;

    /**
     * 收件人电话
     */
    private String shoujianPhone;

    /**
     * 收件地址
     */
    private String shoujianDizhi;

    /**
     * 单位名称
     */
    private String kaipiaoDanwei;

    /**
     * 纳税人识别号
     */
    private String shibiehao;

    /**
     * 地址
     */
    private String kaipiaoDizhi;

    /**
     * 电话
     */
    private String kaipiaoDianhua;

    /**
     * 开户银行
     */
    private String kaipiaoYinhang;

    /**
     * 银行账号
     */
    private String kaipiaoZhanghao;

    private static final long serialVersionUID = 1L;
}