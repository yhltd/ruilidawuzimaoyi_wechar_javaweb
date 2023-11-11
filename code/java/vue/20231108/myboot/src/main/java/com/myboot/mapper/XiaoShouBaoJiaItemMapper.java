package com.myboot.mapper;

import com.myboot.pojo.XiaoShouBaoJiaItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouBaoJiaItemMapper {

    //查询全部
    public List<XiaoShouBaoJiaItem> getAll();

    //增加
    public int baoJiaItemAdd(XiaoShouBaoJiaItem xiaoShouBaoJiaItem);

    //修改
    public int baoJiaItemUpd(XiaoShouBaoJiaItem xiaoShouBaoJiaItem);

    //根据报价id删除
    public int deleteByBaoJiaId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouBaoJiaItem> selectByBaoJiaId(@Param("id") Integer id);


}
