package com.myboot.service.impl;

import com.myboot.mapper.CaiGouRuKuItemMapper;
import com.myboot.pojo.CaiGouRuKuItem;
import com.myboot.service.CaiGouRuKuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiGouRuKuItemImpl implements CaiGouRuKuItemService {

    @Autowired
    private CaiGouRuKuItemMapper mapper;

    @Override
    public List<CaiGouRuKuItem> getAll() {
        List<CaiGouRuKuItem> caiGouRuKuItem = mapper.getAll();
        return caiGouRuKuItem;
    }

    @Override
    public int caiGouRuKuItemAdd(CaiGouRuKuItem caiGouRuKuItem){
        Integer ruKuItem = mapper.caiGouRuKuAdd(caiGouRuKuItem);
        return ruKuItem;
    }

    @Override
    public int caiGouRuKuItemUpd(CaiGouRuKuItem caiGouRuKuItem){
        Integer ruKuItem = mapper.caiGouRuKuUpd(caiGouRuKuItem);
        return ruKuItem;
    }

    @Override
    public int deleteByRuKuItemId(Integer id){
        return mapper.deleteByRuKuItemId(id);
    }

    @Override
    public List<CaiGouRuKuItem> selectByRuKuItemId(Integer id){
        return mapper.selectByRuKuItemId(id);
    }



}
