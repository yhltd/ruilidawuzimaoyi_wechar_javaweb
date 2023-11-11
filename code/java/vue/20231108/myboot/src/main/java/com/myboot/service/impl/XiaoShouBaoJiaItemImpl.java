package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouBaoJiaItemMapper;
import com.myboot.pojo.XiaoShouBaoJiaItem;
import com.myboot.service.XiaoShouBaoJiaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouBaoJiaItemImpl implements XiaoShouBaoJiaItemService {

    @Autowired
    private XiaoShouBaoJiaItemMapper mapper;

    @Override
    public List<XiaoShouBaoJiaItem> getAll() {
        List<XiaoShouBaoJiaItem> xiaoShouBaoJiaItem = mapper.getAll();
        return xiaoShouBaoJiaItem;
    }

    @Override
    public int baoJiaItemAdd(XiaoShouBaoJiaItem xiaoShouBaoJiaItem){
        Integer baoJiaItem = mapper.baoJiaItemAdd(xiaoShouBaoJiaItem);
        return baoJiaItem;
    }

    @Override
    public int baoJiaItemUpd(XiaoShouBaoJiaItem xiaoShouBaoJiaItem){
        Integer baoJiaItem = mapper.baoJiaItemUpd(xiaoShouBaoJiaItem);
        return baoJiaItem;
    }

    @Override
    public int deleteByBaoJiaId(Integer id){
        return mapper.deleteByBaoJiaId(id);
    }

    @Override
    public List<XiaoShouBaoJiaItem> selectByBaoJiaId(Integer id){
        return mapper.selectByBaoJiaId(id);
    }



}
