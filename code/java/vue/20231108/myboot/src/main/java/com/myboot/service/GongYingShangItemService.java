package com.myboot.service;

import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.GongYingShangItem;

import java.util.List;

public interface GongYingShangItemService {

    //根据客户id查询
    public List<GongYingShangItem> selectByGongYingShangId(Integer id);

    //增加
    public int itemAdd(GongYingShangItem gongYingShangItem);

    //修改
    public int itemUpd(GongYingShangItem gongYingShangItem);

    //根据id删除
    public int itemDelById(Integer id);



}