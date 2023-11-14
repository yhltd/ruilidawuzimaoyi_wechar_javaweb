package com.myboot.service;

import com.myboot.pojo.XiaoShouChuKuItem;

import java.util.List;

public interface XiaoShouChuKuItemService {

    //查询全部
    public List<XiaoShouChuKuItem> getAll();

    //增加
    public int chuKuItemAdd(XiaoShouChuKuItem xiaoShouChuKuItem);

    //修改
    public int chuKuItemUpd(XiaoShouChuKuItem xiaoShouChuKuItem);

    //根据入库id删除
    public int deleteByChuKuItemId(Integer id);

    //根据入库id查询
    public List<XiaoShouChuKuItem> selectByChuKuItemId(Integer id);

}