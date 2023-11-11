package com.myboot.service.impl;

import com.myboot.mapper.CustomerItemMapper;
import com.myboot.mapper.GongYingShangItemMapper;
import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.GongYingShangItem;
import com.myboot.service.CustomerItemService;
import com.myboot.service.GongYingShangItemService;
import com.myboot.service.GongYingShangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GongYingShangItemImpl implements GongYingShangItemService {

    @Autowired
    private GongYingShangItemMapper mapper;

    @Override
    public int itemAdd(GongYingShangItem gongYingShangItem){
        Integer item = mapper.itemAdd(gongYingShangItem);
        return item;
    }

    @Override
    public int itemUpd(GongYingShangItem gongYingShangItem){
        Integer item = mapper.itemUpd(gongYingShangItem);
        return item;
    }

    @Override
    public int itemDelById(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public List<GongYingShangItem> selectByGongYingShangId(Integer id){
        return mapper.selectByGongYingShangId(id);
    }



}
