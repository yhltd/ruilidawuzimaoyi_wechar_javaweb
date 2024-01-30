package com.myboot.mapper;

import com.myboot.pojo.QiChuKeHu;
import com.myboot.pojo.QiChuProduct;
import com.myboot.pojo.ZhuanZhang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QiChuProductMapper {

    //查询全部
    public List<QiChuProduct> getAll();

    //增加
    public int add(QiChuProduct qiChuProduct);

    //修改
    public int upd(QiChuProduct qiChuProduct);

    //根据id删除
    public int del(@Param("id") Integer id);

}
