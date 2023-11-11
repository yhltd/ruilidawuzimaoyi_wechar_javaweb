package com.myboot.service;

import com.myboot.pojo.CaiGouRuKuItem;

import java.util.List;

public interface CaiGouRuKuItemService {

    //查询全部
    public List<CaiGouRuKuItem> getAll();

    //增加
    public int caiGouRuKuItemAdd(CaiGouRuKuItem caiGouRuKuItem);

    //修改
    public int caiGouRuKuItemUpd(CaiGouRuKuItem caiGouRuKuItem);

    //根据入库id删除
    public int deleteByRuKuItemId(Integer id);

    //根据入库id查询
    public List<CaiGouRuKuItem> selectByRuKuItemId(Integer id);

}