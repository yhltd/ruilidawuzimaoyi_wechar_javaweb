package com.myboot.mapper;

import com.myboot.pojo.XiaoShouChuKuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouChuKuItemMapper {

    //查询全部
    public List<XiaoShouChuKuItem> getAll();

    //增加
    public int chuKuItemAdd(XiaoShouChuKuItem xiaoShouChuKuItem);

    //修改
    public int chuKuItemUpd(XiaoShouChuKuItem xiaoShouChuKuItem);

    //根据采购id删除
    public int deleteByChuKuItemId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouChuKuItem> selectByChuKuItemId(@Param("id") Integer id);


}
