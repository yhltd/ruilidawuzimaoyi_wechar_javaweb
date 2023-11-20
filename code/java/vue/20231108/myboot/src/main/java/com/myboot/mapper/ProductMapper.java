package com.myboot.mapper;

import com.myboot.pojo.Customer;
import com.myboot.pojo.GongYingShang;
import com.myboot.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    //查询全部
    public List<Product> getAll();

    //条件查询
    public List<Product> queryList(String name,String type,String enable);

    //增加
    public int productAdd(Product product);

    //修改
    public int productUpd(Product product);

    //根据采购id删除
    public int deleteById(@Param("id") Integer id);

    //根据采购id查询
    public List<Product> selectById(@Param("id") Integer id);

    //获取采购单可选择的产品
    public List<Product> selectCaiGouProduct();

    public List<Product> selectMaxDanHao();
}
