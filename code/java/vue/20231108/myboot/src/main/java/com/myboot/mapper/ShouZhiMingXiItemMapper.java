package com.myboot.mapper;

import com.myboot.pojo.ShouZhiMingXiItem;
import com.myboot.pojo.XiaoShouBaoJiaItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShouZhiMingXiItemMapper {

    //查询全部
    public List<ShouZhiMingXiItem> getAll();

    //增加
    public int shouZhiItemAdd(ShouZhiMingXiItem shouZhiMingXiItem);

    //修改
    public int shouZhiItemUpd(ShouZhiMingXiItem shouZhiMingXiItem);

    //根据收支id删除
    public boolean deleteByShouZhiId(@Param("id") Integer id);

    //根据收支id查询
    public List<ShouZhiMingXiItem> selectByShouZhiId(@Param("id") Integer id);


}
