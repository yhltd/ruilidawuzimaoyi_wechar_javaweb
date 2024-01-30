package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.mapper.CustomerItemMapper;
import com.myboot.mapper.CustomerMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.Customer;
import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.GongYingShang;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerItemMapper itemMapper;

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = mapper.getAll();
        return customers;
    }

    @Override
    public List<Customer> queryList(String bianhao,String name){
        List<Customer> customers = mapper.queryList(bianhao,name);
        return customers;
    }

    @Override
    public int customerAdd(Customer customer){
        int dingDan = mapper.customerAdd(customer);
        return dingDan;
    }

    @Override
    public int customerUpd(Customer customer){
        int dingDan = mapper.customerUpd(customer);
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
    public List<Customer> selectById(Integer id){
        return mapper.selectById(id);
    }

    @Override
    public List<Customer> selectMaxDanHao(){
        return mapper.selectMaxDanHao();
    }

    @Override
    public List<Customer> getChongFu(String this_where) {
        List<Customer> customers = mapper.getChongFu(this_where);
        return customers;
    }
}
