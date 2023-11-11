package com.myboot.service;

import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.CustomerItem;

import java.util.List;

public interface CustomerItemService {

    //根据客户id查询
    public List<CustomerItem> selectByCustomerId(Integer id);

    //增加
    public int itemAdd(CustomerItem customerItem);

    //修改
    public int itemUpd(CustomerItem customerItem);

    //根据采购id删除
    public int itemDelById(Integer id);



}