package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouDingDanItemMapper;
import com.myboot.pojo.XiaoShouDingDanItem;
import com.myboot.service.XiaoShouDingDanItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouDingDanItemImpl implements XiaoShouDingDanItemService {

    @Autowired
    private XiaoShouDingDanItemMapper mapper;

    @Override
    public List<XiaoShouDingDanItem> getAll() {
        List<XiaoShouDingDanItem> xiaoShouDingDanItem = mapper.getAll();
        return xiaoShouDingDanItem;
    }

    @Override
    public int xiaoShouItemAdd(XiaoShouDingDanItem xiaoShouDingDanItem){
        Integer dingDanItem = mapper.xiaoShouItemAdd(xiaoShouDingDanItem);
        return dingDanItem;
    }

    @Override
    public int xiaoShouItemUpd(XiaoShouDingDanItem xiaoShouDingDanItem){
        Integer dingDanItem = mapper.xiaoShouItemUpd(xiaoShouDingDanItem);
        return dingDanItem;
    }

    @Override
    public int deleteByXiaoShouId(Integer id){
        return mapper.deleteByXiaoShouId(id);
    }

    @Override
    public List<XiaoShouDingDanItem> selectByXiaoShouId(Integer id){
        return mapper.selectByXiaoShouId(id);
    }


}
