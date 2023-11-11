package com.myboot.service;

import com.myboot.pojo.Goods;

import java.util.List;

public interface GoodsService {
    public List<Goods> getAll();
    public boolean goodsAdd(Goods goods);
    public boolean goodsUpdate(Goods goods);
    public boolean goodsDel(Integer id);
}
