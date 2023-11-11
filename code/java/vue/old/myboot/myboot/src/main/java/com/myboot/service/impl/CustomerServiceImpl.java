package com.myboot.service.impl;

import com.myboot.mapper.CustomerMapper;
import com.myboot.pojo.Customer;
import com.myboot.service.CustomerService;
import com.sun.org.apache.bcel.internal.generic.DALOAD;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = mapper.getAll();
        return customers;
    }

    @Override
    public boolean customerAdd(Customer customer) {
        boolean flag = false;
        int result = mapper.customerAdd(customer);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean customerUpdate(Customer customer) {
        boolean flag = false;
        int result = mapper.customerUpdate(customer);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean customerDelete(String id) {
        boolean flag = false;
        int result = mapper.customerDelete(id);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }
}
