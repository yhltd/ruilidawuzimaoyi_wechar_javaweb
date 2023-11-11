package com.myboot.mapper;

import com.myboot.pojo.AccountingUnit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountingUnitMapper {
    //查询
    public List<AccountingUnit> getAll();
    //增加
    public int accountAdd(@Param("aName") String aName);
    //修改
    public int accountUpdate(@Param("aName") String aName,@Param("aId") Integer aId);
    //删除
    public int accountDelete(@Param("aId") Integer aId);
}
