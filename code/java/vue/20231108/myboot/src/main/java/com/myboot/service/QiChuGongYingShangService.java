package com.myboot.service;

import com.myboot.pojo.JiZhang;
import com.myboot.pojo.QiChuGongYingShang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QiChuGongYingShangService {


    //查询全部
    public List<QiChuGongYingShang> getAll();

    //增加
    public int add(QiChuGongYingShang qiChuGongYingShang);

    //修改
    public int upd(QiChuGongYingShang qiChuGongYingShang);

    //根据id删除
    public int del(List<Integer> list);


}