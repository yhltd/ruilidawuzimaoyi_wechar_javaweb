package com.myboot.service;

import com.myboot.pojo.JiZhang;
import com.myboot.pojo.QiChuKeHu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QiChuKeHuService {


    //查询全部
    public List<QiChuKeHu> getAll();

    //增加
    public int add(QiChuKeHu qiChuKeHu);

    //修改
    public int upd(QiChuKeHu qiChuKeHu);

    //根据id删除
    public int del(List<Integer> list);


}