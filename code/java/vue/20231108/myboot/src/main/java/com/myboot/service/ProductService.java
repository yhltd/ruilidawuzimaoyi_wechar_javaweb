package com.myboot.service;

import com.myboot.pojo.Customer;
import com.myboot.pojo.GongYingShang;
import com.myboot.pojo.Product;

import java.util.List;

public interface ProductService {

    //查询全部
    public List<Product> getAll();

    //条件查询
    public List<Product> queryList(String name,String type,String enable);

    //增加
    public int productAdd(Product product);

    //修改
    public int productUpd(Product product);

    //根据id删除
    public int deleteById(List<Integer> list);

    //根据id查询
    public List<Product> selectById(Integer id);

    //获取采购单可选择的产品
    public List<Product> selectCaiGouProduct();

    //获取销售单可选择的产品
    public List<Product> selectXiaoShouProduct();

    public List<Product> selectMaxDanHao();

}