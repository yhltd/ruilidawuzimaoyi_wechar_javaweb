package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouChuKuItemMapper;
import com.myboot.pojo.XiaoShouChuKuItem;
import com.myboot.service.XiaoShouChuKuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouChuKuItemImpl implements XiaoShouChuKuItemService {

    @Autowired
    private XiaoShouChuKuItemMapper mapper;

    @Override
    public List<XiaoShouChuKuItem> getAll() {
        List<XiaoShouChuKuItem> xiaoShouChuKuItem = mapper.getAll();
        return xiaoShouChuKuItem;
    }

    @Override
    public int chuKuItemAdd(XiaoShouChuKuItem xiaoShouChuKuItem){
        Integer chuKuItem = mapper.chuKuItemAdd(xiaoShouChuKuItem);
        return chuKuItem;
    }

    @Override
    public int chuKuItemUpd(XiaoShouChuKuItem xiaoShouChuKuItem){
        Integer chuKuItem = mapper.chuKuItemUpd(xiaoShouChuKuItem);
        return chuKuItem;
    }

    @Override
    public int deleteByChuKuItemId(Integer id){
        return mapper.deleteByChuKuItemId(id);
    }

    @Override
    public List<XiaoShouChuKuItem> selectByChuKuItemId(Integer id){
        return mapper.selectByChuKuItemId(id);
    }



}
