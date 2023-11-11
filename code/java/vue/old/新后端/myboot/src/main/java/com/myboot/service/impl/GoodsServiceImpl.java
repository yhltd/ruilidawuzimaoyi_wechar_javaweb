package com.myboot.service.impl;

import com.myboot.mapper.GoodsMapper;
import com.myboot.pojo.Goods;
import com.myboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper mapper;

    @Override
    public List<Goods> getAll() {
        List<Goods> goods = mapper.getAll();
        return goods;
    }

    @Override
    public boolean goodsAdd(Goods goods) {
        boolean flag = false;
        int result = mapper.goodsAdd(goods);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean goodsUpdate(Goods goods) {
        boolean flag = false;
        int result = mapper.goodsUpdate(goods);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean goodsDel(Integer id) {
        boolean flag = false;
        int result = mapper.goodsDel(id);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }
}
