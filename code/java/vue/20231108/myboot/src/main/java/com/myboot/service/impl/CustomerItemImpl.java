package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CustomerItemMapper;
import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.CustomerItem;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CustomerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerItemImpl implements CustomerItemService {

    @Autowired
    private CustomerItemMapper mapper;

    @Override
    public int itemAdd(CustomerItem customerItem){
        Integer item = mapper.itemAdd(customerItem);
        return item;
    }

    @Override
    public int itemUpd(CustomerItem customerItem){
        Integer item = mapper.itemUpd(customerItem);
        return item;
    }

    @Override
    public int itemDelById(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public List<CustomerItem> selectByCustomerId(Integer id){
        return mapper.selectByCustomerId(id);
    }



}
