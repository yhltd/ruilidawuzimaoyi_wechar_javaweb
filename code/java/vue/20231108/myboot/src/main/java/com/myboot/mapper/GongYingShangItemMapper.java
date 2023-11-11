package com.myboot.mapper;

import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.GongYingShangItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GongYingShangItemMapper {


    //增加
    public int itemAdd(GongYingShangItem gongYingShangItem);

    //修改
    public int itemUpd(GongYingShangItem gongYingShangItem);

    //根据id删除
    public int deleteById(@Param("id") Integer id);

    //根据id查询
    public List<GongYingShangItem> selectByGongYingShangId(@Param("id") Integer id);


}
