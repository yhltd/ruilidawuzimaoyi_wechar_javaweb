package com.myboot.service;

import com.myboot.pojo.JiZhang;
import com.myboot.pojo.QiChuKeHu;
import com.myboot.pojo.QiChuProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QiChuProductService {


    //查询全部
    public List<QiChuProduct> getAll();

    //增加
    public int add(QiChuProduct qiChuProduct);

    //修改
    public int upd(QiChuProduct qiChuProduct);

    //根据id删除
    public int del(List<Integer> list);


}