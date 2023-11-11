package com.myboot.mapper;

import com.myboot.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    public List<Goods> getAll();
    public int goodsAdd(Goods goods);
    public int goodsUpdate(Goods goods);
    public int goodsDel(@Param("gId") Integer id);
}
