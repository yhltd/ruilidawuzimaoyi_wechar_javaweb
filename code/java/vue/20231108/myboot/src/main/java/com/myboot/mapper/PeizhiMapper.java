package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.Peizhi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/8 11:22
 */
@Mapper
public interface PeizhiMapper {

    List<Peizhi> queryAllByType(String type);

    //增加
    public int peiZhiAdd(Peizhi peizhi);

    //修改
    public int peiZhiUpd(Peizhi peizhi);

    //根据采购id删除
    public int deleteByPeiZhiId(Integer id);

    //根据采购id查询
    public List<Peizhi> selectByPeiZhiId(Integer id);

}
