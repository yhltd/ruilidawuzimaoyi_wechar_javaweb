package com.myboot.mapper;

import com.myboot.pojo.Customer;
import com.myboot.pojo.PeiZhiShuiLv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PeiZhiShuiLvMapper {

    //查询全部
    public List<PeiZhiShuiLv> getAll();

    //修改
    public int update(PeiZhiShuiLv peiZhiShuiLv);

}
