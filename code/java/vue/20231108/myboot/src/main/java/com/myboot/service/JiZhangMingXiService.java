package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanXiangQing;
import com.myboot.pojo.JiZhangMingXi;

import java.util.List;

public interface JiZhangMingXiService {

    //查询全部
    public List<JiZhangMingXi> getAll();

    //修改
    public int updateById(JiZhangMingXi jiZhangMingXi);

}