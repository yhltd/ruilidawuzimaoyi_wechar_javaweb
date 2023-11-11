package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.service.CaiGouDingDanItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiGouDingDanItemImpl implements CaiGouDingDanItemService {

    @Autowired
    private CaiGouDingDanItemMapper mapper;

    @Override
    public List<CaiGouDingDanItem> getAll() {
        List<CaiGouDingDanItem> caiGouDingDanItem = mapper.getAll();
        return caiGouDingDanItem;
    }

    @Override
    public int caiGouDingDanItemAdd(CaiGouDingDanItem caiGouDingDanItem){
        Integer dingDanItem = mapper.caiGouDingDanAdd(caiGouDingDanItem);
        return dingDanItem;
    }

    @Override
    public int caiGouDingDanItemUpd(CaiGouDingDanItem caiGouDingDanItem){
        int dingDanItem = mapper.caiGouDingDanUpd(caiGouDingDanItem);
        return dingDanItem;
    }

    @Override
    public boolean deleteByCaiGouId(Integer id){
        return mapper.deleteByCaiGouId(id);
    }

    @Override
    public List<CaiGouDingDanItem> selectByCaiGouId(Integer id){
        return mapper.selectByCaiGouId(id);
    }



}
