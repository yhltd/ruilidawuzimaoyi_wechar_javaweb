package com.myboot.service.impl;

import com.myboot.mapper.CustomerItemMapper;
import com.myboot.mapper.ProductItemMapper;
import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.ProductItem;
import com.myboot.service.CustomerItemService;
import com.myboot.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductItemImpl implements ProductItemService {

    @Autowired
    private ProductItemMapper mapper;

    @Override
    public int itemAdd(ProductItem productItem){
        Integer item = mapper.itemAdd(productItem);
        return item;
    }

    @Override
    public int itemUpd(ProductItem productItem){
        Integer item = mapper.itemUpd(productItem);
        return item;
    }

    @Override
    public int itemDelById(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public List<ProductItem> selectByProductId(Integer id){
        return mapper.selectByProductId(id);
    }



}
