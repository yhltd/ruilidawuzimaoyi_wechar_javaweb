package com.myboot.service;


import com.myboot.dto.CustomerDto;
import com.myboot.dto.CustomerSearch;

import java.util.List;

public interface CustomerService {

    public List<CustomerDto> getCustomer(CustomerSearch customerSearch);

    public boolean customerAdd(CustomerDto customerDto);

    boolean customerUpdate(CustomerDto customerDto);

    public boolean customerDelete(int id);

    public CustomerDto detail(int id);
}
