package com.myboot.service;

import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.ProductItem;

import java.util.List;

public interface ProductItemService {

    //根据id查询
    public List<ProductItem> selectByProductId(Integer id);

    //增加
    public int itemAdd(ProductItem productItem);

    //修改
    public int itemUpd(ProductItem productItem);

    //根据采购id删除
    public int itemDelById(Integer id);



}