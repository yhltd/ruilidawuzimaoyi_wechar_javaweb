package com.myboot.service;

import com.myboot.pojo.CaiGouDingDanItem;

import java.util.List;

public interface CaiGouDingDanItemService {

    //查询全部
    public List<CaiGouDingDanItem> getAll();

    //根据采购id查询
    public List<CaiGouDingDanItem> selectByCaiGouId(Integer id);

    //增加
    public int caiGouDingDanItemAdd(CaiGouDingDanItem caiGouDingDanItem);

    //修改
    public int caiGouDingDanItemUpd(CaiGouDingDanItem caiGouDingDanItem);

    //根据采购id删除
    public boolean deleteByCaiGouId(Integer id);



}