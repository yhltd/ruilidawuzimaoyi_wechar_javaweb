package com.myboot.mapper;

import com.myboot.pojo.XiaoShouDingDanItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouDingDanItemMapper {

    //查询全部
    public List<XiaoShouDingDanItem> getAll();

    //增加
    public int xiaoShouItemAdd(XiaoShouDingDanItem xiaoShouDingDanItem);

    //修改
    public int xiaoShouItemUpd(XiaoShouDingDanItem xiaoShouDingDanItem);

    //根据报价id删除
    public int deleteByXiaoShouId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouDingDanItem> selectByXiaoShouId(@Param("id") Integer id);


}
