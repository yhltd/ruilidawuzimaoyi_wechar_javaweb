package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.Customer;
import com.myboot.pojo.GongYingShang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {

    //查询全部
    public List<Customer> getAll();

    //条件查询
    public List<Customer> queryList(String bianhao,String name);

    //增加
    public int customerAdd(Customer customer);

    //修改
    public int customerUpd(Customer customer);

    //根据采购id删除
    public int deleteById(@Param("id") Integer id);

    //根据采购id查询
    public List<Customer> selectById(@Param("id") Integer id);

    public List<Customer> selectMaxDanHao();

    //查询重复
    public List<Customer> getChongFu(String this_where);
}
