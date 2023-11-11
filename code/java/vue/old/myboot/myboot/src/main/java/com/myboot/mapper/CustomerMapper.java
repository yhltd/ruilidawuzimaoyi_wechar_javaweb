package com.myboot.mapper;

import com.myboot.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {
    //查询
    public List<Customer> getAll();
    //增加
    public int customerAdd(Customer customer);
    //修改
    public int customerUpdate(Customer customer);
    //删除
    public int customerDelete(@Param("cId") String id);
}
