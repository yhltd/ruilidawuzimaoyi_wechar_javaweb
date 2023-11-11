package com.myboot.pojo;

import lombok.Data;

/**
 * 用户表-配置表
 * @author hui
 * @date 2023/11/8 9:09
 */
@Data
public class User_Peizhi {
    private Integer id;
    private String userName;
    private String name;
    private Integer password;
    private String power;
    private String shenpi;
    private Peizhi cangku;
    private Peizhi dianpu;
    private Peizhi hesuanDanwei;
}
