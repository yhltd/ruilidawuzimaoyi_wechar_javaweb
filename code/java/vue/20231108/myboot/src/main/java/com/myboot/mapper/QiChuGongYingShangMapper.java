package com.myboot.mapper;

import com.myboot.pojo.QiChuGongYingShang;
import com.myboot.pojo.QiChuKeHu;
import com.myboot.pojo.ZhuanZhang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QiChuGongYingShangMapper {

    //查询全部
    public List<QiChuGongYingShang> getAll();

    //增加
    public int add(QiChuGongYingShang qiChuGongYingShang);

    //修改
    public int upd(QiChuGongYingShang qiChuGongYingShang);

    //根据id删除
    public int del(@Param("id") Integer id);

}
