package com.myboot.service;


import com.myboot.pojo.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getAll();
    public boolean customerAdd(Customer customer);
    public boolean customerUpdate(Customer customer);
    public boolean customerDelete(String id);
}
