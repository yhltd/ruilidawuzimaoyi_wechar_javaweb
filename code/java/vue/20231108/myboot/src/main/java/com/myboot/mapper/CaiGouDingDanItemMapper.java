package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDanItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouDingDanItemMapper {

    //查询全部
    public List<CaiGouDingDanItem> getAll();

    //增加
    public int caiGouDingDanAdd(CaiGouDingDanItem caiGouDingDanItem);

    //修改
    public int caiGouDingDanUpd(CaiGouDingDanItem caiGouDingDanItem);

    //根据采购id删除
    public boolean deleteByCaiGouId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouDingDanItem> selectByCaiGouId(@Param("id") Integer id);


}
