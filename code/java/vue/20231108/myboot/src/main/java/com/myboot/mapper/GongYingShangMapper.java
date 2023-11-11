package com.myboot.mapper;

import com.myboot.pojo.Customer;
import com.myboot.pojo.GongYingShang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GongYingShangMapper {

    //查询全部
    public List<GongYingShang> getAll();

    //条件查询
    public List<GongYingShang> queryList(String bianhao,String name);

    //增加
    public int gongyingshangAdd(GongYingShang gongYingShang);

    //修改
    public int gongyingshangupd(GongYingShang gongYingShang);

    //根据采购id删除
    public int deleteById(@Param("id") Integer id);

    //根据采购id查询
    public List<GongYingShang> selectById(@Param("id") Integer id);


}
