package com.myboot.mapper;

import com.myboot.pojo.QiChuKeHu;
import com.myboot.pojo.ZhuanZhang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QiChuKeHuMapper {

    //查询全部
    public List<QiChuKeHu> getAll();

    //增加
    public int add(QiChuKeHu qiChuKeHu);

    //修改
    public int upd(QiChuKeHu qiChuKeHu);

    //根据id删除
    public int del(@Param("id") Integer id);

}
