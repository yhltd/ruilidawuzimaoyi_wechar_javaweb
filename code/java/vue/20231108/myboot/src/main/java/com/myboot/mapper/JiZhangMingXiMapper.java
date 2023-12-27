package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanXiangQing;
import com.myboot.pojo.JiZhangMingXi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JiZhangMingXiMapper {

    //查询全部
    public List<JiZhangMingXi> getAll();

    //修改
    public int updateById(JiZhangMingXi jiZhangMingXi);

}
