package com.myboot.service.impl;

import com.myboot.dto.CustomerDto;
import com.myboot.dto.CustomerSearch;
import com.myboot.mapper.CustomerItemMapper;
import com.myboot.mapper.CustomerMapper;
import com.myboot.pojo.Customer;
import com.myboot.pojo.CustomerExample;
import com.myboot.pojo.CustomerItem;
import com.myboot.pojo.CustomerItemExample;
import com.myboot.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerItemMapper customerItemMapper;

    @Override
    public List<CustomerDto> getCustomer(CustomerSearch customerSearch) {
        CustomerExample example = getExample(customerSearch);
        List<Customer> customersList = customerMapper.selectByExample(example);
        List<String> CustomerIdList = customersList.stream().map(s -> String.valueOf(s.getId())).collect(Collectors.toList());
        CustomerItemExample customerItemExample = new CustomerItemExample();
        customerItemExample.createCriteria().andCustomerIdIn(CustomerIdList);
        List<CustomerItem> customerItemList = customerItemMapper.selectByExample(customerItemExample);
        Map<String, List<CustomerItem>> CustomerItemMap = new HashMap<>();
        for (CustomerItem customerItem : customerItemList) {
            CustomerItemMap.computeIfAbsent(customerItem.getCustomerId(), k -> new ArrayList<CustomerItem>()).add(customerItem);
        }
        List<CustomerDto> result = new ArrayList<>();
        for (Customer customer : customersList) {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            List<CustomerItem> customerItems = CustomerItemMap.get(String.valueOf(customer.getId()));
            customerDto.setCustomerItemList(customerItems);
            result.add(customerDto);
        }
        return result;
    }

    @Override
    public boolean customerAdd(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customerMapper.insert(customer);
        for (CustomerItem customerItem : customerDto.getCustomerItemList()) {
            customerItem.setCustomerId(String.valueOf(customer.getId()));
            customerItemMapper.insert(customerItem);
        }
        return true;
    }

    @Override
    public boolean customerUpdate(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customerMapper.updateByPrimaryKey(customer);
        for (CustomerItem customerItem : customerDto.getCustomerItemList()) {
            customerItemMapper.updateByPrimaryKey(customerItem);
        }
        return true;
    }

    @Override
    public boolean customerDelete(int id) {
        customerMapper.deleteByPrimaryKey(id);
        CustomerItemExample example = new CustomerItemExample();
        example.createCriteria().andCustomerIdEqualTo(String.valueOf(id));

        customerItemMapper.deleteByExample(example);
        return true;
    }

    @Override
    public CustomerDto detail(int id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        CustomerItemExample example = new CustomerItemExample();
        example.createCriteria().andCustomerIdEqualTo(String.valueOf(id));
        List<CustomerItem> customerItemList = customerItemMapper.selectByExample(example);
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        customerDto.setCustomerItemList(customerItemList);
        return customerDto;
    }

    private CustomerExample getExample(CustomerSearch customerSearch) {
        CustomerExample example = new CustomerExample();
        example.setOffset((customerSearch.getCurrentPage() - 1L) * customerSearch.getPageSize());
        example.setLimit(customerSearch.getPageSize());
        if (customerSearch.getBianhao() != null) {
            example.createCriteria().andBianhaoEqualTo(customerSearch.getBianhao());
        }
        if (customerSearch.getName() != null) {
            example.createCriteria().andBianhaoEqualTo(customerSearch.getName());
        }
        return example;

    }
}
