package com.myboot.service;

import com.myboot.pojo.XiaoShouDingDanItem;

import java.util.List;

public interface XiaoShouDingDanItemService {

    //查询全部
    public List<XiaoShouDingDanItem> getAll();

    //根据报价id查询
    public List<XiaoShouDingDanItem> selectByXiaoShouId(Integer id);

    //增加
    public int xiaoShouItemAdd(XiaoShouDingDanItem xiaoShouDingDanItem);

    //修改
    public int xiaoShouItemUpd(XiaoShouDingDanItem xiaoShouDingDanItem);

    //根据报价id删除
    public int deleteByXiaoShouId(Integer id);

}