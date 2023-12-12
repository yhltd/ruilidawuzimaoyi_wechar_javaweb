package com.myboot.pojo;


import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class YiBiaoPan {
    private Integer id;
    private String kehu;
    private String gongyingshang;
    private String qiankuan;
    private String name;
    private String num;
    private String money;
    private String xiaoshou;
    private String chengben;
    private String maoli;
    private String riqi;
    private String yewuyuan;
    private String dingdan;
    private String chuku;
    private String shoukuan;
    private String jiashuiXiaoji;

    @NumberFormat(pattern = "#.##")
    public String getQiankuan() {
        return qiankuan;
    }

    public void setQiankuan(String qiankuan) {
        this.qiankuan = qiankuan;
    }

    @NumberFormat(pattern = "#.##")
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @NumberFormat(pattern = "#.##")
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @NumberFormat(pattern = "#.##")
    public String getXiaoshou() {
        return xiaoshou;
    }

    public void setXiaoshou(String xiaoshou) {
        this.xiaoshou = xiaoshou;
    }

    @NumberFormat(pattern = "#.##")
    public String getChengben() {
        return chengben;
    }

    public void setChengben(String chengben) {
        this.chengben = chengben;
    }

    @NumberFormat(pattern = "#.##")
    public String getMaoli() {
        return maoli;
    }

    public void setMaoli(String maoli) {
        this.maoli = maoli;
    }

    @NumberFormat(pattern = "#.##")
    public String getDingdan() {
        return dingdan;
    }

    public void setDingdan(String dingdan) {
        this.dingdan = dingdan;
    }

    @NumberFormat(pattern = "#.##")
    public String getChuku() {
        return chuku;
    }

    public void setChuku(String chuku) {
        this.chuku = chuku;
    }

    @NumberFormat(pattern = "#.##")
    public String getShoukuan() {
        return shoukuan;
    }

    public void setShoukuan(String shoukuan) {
        this.shoukuan = shoukuan;
    }

    @NumberFormat(pattern = "#.##")
    public String getJiashuiXiaoji() {
        return jiashuiXiaoji;
    }

    public void setJiashuiXiaoji(String jiashuiXiaoji) {
        this.jiashuiXiaoji = jiashuiXiaoji;
    }
}
