package com.myboot.service;

import com.myboot.pojo.ShouZhiMingXi;
import com.myboot.pojo.ShouZhiMingXiItem;
import com.myboot.pojo.XiaoShouBaoJiaItem;

import java.util.List;

public interface ShouZhiMingXiItemService {

    //查询全部
    public List<ShouZhiMingXiItem> getAll();

    //根据收支id查询
    public List<ShouZhiMingXiItem> selectByShouZhiId(Integer id);

    //增加
    public int shouZhiItemAdd(ShouZhiMingXiItem shouZhiMingXiItem);

    //修改
    public int shouZhiItemUpd(ShouZhiMingXiItem shouZhiMingXiItem);

    //根据报价id删除
    public boolean deleteByShouZhiId(Integer id);

}