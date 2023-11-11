package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.Customer;

import java.util.List;

public interface CustomerService {

    //查询全部
    public List<Customer> getAll();

    //条件查询
    public List<Customer> queryList(String bianhao,String name);

    //增加
    public int customerAdd(Customer customer);

    //修改
    public int customerUpd(Customer customer);

    //根据id删除
    public int deleteById(List<Integer> list);

    //根据采购id查询
    public List<Customer> selectById(Integer id);

}