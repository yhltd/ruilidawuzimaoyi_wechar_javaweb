package com.myboot.service;

import com.myboot.pojo.XiaoShouBaoJiaItem;

import java.util.List;

public interface XiaoShouBaoJiaItemService {

    //查询全部
    public List<XiaoShouBaoJiaItem> getAll();

    //根据报价id查询
    public List<XiaoShouBaoJiaItem> selectByBaoJiaId(Integer id);

    //增加
    public int baoJiaItemAdd(XiaoShouBaoJiaItem xiaoShouBaoJiaItem);

    //修改
    public int baoJiaItemUpd(XiaoShouBaoJiaItem xiaoShouBaoJiaItem);

    //根据报价id删除
    public int deleteByBaoJiaId(Integer id);

}