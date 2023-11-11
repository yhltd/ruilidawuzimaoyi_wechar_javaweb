package com.myboot.service.impl;

import com.myboot.mapper.ShouZhiMingXiItemMapper;
import com.myboot.mapper.XiaoShouDingDanItemMapper;
import com.myboot.pojo.ShouZhiMingXiItem;
import com.myboot.pojo.XiaoShouDingDanItem;
import com.myboot.service.ShouZhiMingXiItemService;
import com.myboot.service.XiaoShouDingDanItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShouZhiMingXiItemImpl implements ShouZhiMingXiItemService {

    @Autowired
    private ShouZhiMingXiItemMapper mapper;

    @Override
    public List<ShouZhiMingXiItem> getAll() {
        List<ShouZhiMingXiItem> shouZhiMingXiItem = mapper.getAll();
        return shouZhiMingXiItem;
    }

    @Override
    public int shouZhiItemAdd(ShouZhiMingXiItem shouZhiMingXiItem){
        Integer shouZhiItem = mapper.shouZhiItemAdd(shouZhiMingXiItem);
        return shouZhiItem;
    }

    @Override
    public int shouZhiItemUpd(ShouZhiMingXiItem shouZhiMingXiItem){
        Integer shouZhiItem = mapper.shouZhiItemUpd(shouZhiMingXiItem);
        return shouZhiItem;
    }

    @Override
    public boolean deleteByShouZhiId(Integer id){
        return mapper.deleteByShouZhiId(id);
    }

    @Override
    public List<ShouZhiMingXiItem> selectByShouZhiId(Integer id){
        return mapper.selectByShouZhiId(id);
    }


}
