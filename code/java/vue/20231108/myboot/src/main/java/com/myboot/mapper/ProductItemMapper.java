package com.myboot.mapper;

import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.ProductItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductItemMapper {


    //增加
    public int itemAdd(ProductItem productItem);

    //修改
    public int itemUpd(ProductItem productItem);

    //根据id删除
    public int deleteById(@Param("id") Integer id);

    //根据id查询
    public List<ProductItem> selectByProductId(@Param("id") Integer id);


}
