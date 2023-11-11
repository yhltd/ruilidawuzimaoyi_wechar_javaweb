package com.myboot.pojo;

import lombok.Data;

@Data
public class UserAndPower {
    private Integer id;
    private String userName;
    private String name;
    private Integer password;
    private String power;
    private String shenpi;
    private String cangku;
    private String dianpu;
    private String hesuanDanwei;
    private String changyong;
    private UserPower userPower;
}
