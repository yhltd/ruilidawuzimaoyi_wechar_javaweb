package com.myboot.mapper;

import com.myboot.pojo.CaiGouRuKuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouRuKuItemMapper {

    //查询全部
    public List<CaiGouRuKuItem> getAll();

    //增加
    public int caiGouRuKuAdd(CaiGouRuKuItem caiGouRuKuItem);

    //修改
    public int caiGouRuKuUpd(CaiGouRuKuItem caiGouRuKuItem);

    //根据入库id删除
    public int deleteByRuKuItemId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouRuKuItem> selectByRuKuItemId(@Param("id") Integer id);


}
