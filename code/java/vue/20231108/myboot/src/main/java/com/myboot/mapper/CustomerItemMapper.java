package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.CustomerItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerItemMapper {


    //增加
    public int itemAdd(CustomerItem customerItem);

    //修改
    public int itemUpd(CustomerItem customerItem);

    //根据id删除
    public int deleteById(@Param("id") Integer id);

    //根据id查询
    public List<CustomerItem> selectByCustomerId(@Param("id") Integer id);


}
