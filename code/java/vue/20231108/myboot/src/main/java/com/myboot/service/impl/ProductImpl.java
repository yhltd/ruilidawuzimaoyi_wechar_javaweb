package com.myboot.service.impl;

import com.myboot.mapper.CustomerItemMapper;
import com.myboot.mapper.CustomerMapper;
import com.myboot.mapper.ProductItemMapper;
import com.myboot.mapper.ProductMapper;
import com.myboot.pojo.Customer;
import com.myboot.pojo.Product;
import com.myboot.service.CustomerService;
import com.myboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductItemMapper itemMapper;

    @Override
    public List<Product> getAll() {
        List<Product> products = mapper.getAll();
        return products;
    }

    @Override
    public List<Product> queryList(String name,String type,String enable){
        List<Product> products = mapper.queryList(name,type,enable);
        return products;
    }

    @Override
    public int productAdd(Product product){
        int dingDan = mapper.productAdd(product);
        return dingDan;
    }

    @Override
    public int productUpd(Product product){
        int dingDan = mapper.productUpd(product);
        return dingDan;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(List<Integer> list){
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteById(id);
            itemMapper.deleteById(id);
        }
        return count;
    }

    @Override
    public List<Product> selectById(Integer id){
        return mapper.selectById(id);
    }


    @Override
    public List<Product> selectCaiGouProduct(){
        return mapper.selectCaiGouProduct();
    }

}
