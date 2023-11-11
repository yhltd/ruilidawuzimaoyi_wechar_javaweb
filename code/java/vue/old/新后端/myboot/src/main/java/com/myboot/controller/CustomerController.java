package com.myboot.controller;

import com.myboot.dto.CustomerDto;
import com.myboot.dto.CustomerSearch;
import com.myboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@ResponseBody
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public List<CustomerDto> getAll(@RequestBody CustomerSearch customerSearch) {
        return customerService.getCustomer(customerSearch);
    }

    @PostMapping("/add")
    public String customerAdd(@RequestBody CustomerDto customerDto) {
        boolean result = customerService.customerAdd(customerDto);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String customerUpdate(@RequestBody CustomerDto customerDto) {
        boolean result = customerService.customerUpdate(customerDto);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    @DeleteMapping("/delete/{cId}")
    public String customerDelete(@PathVariable("cId") int id) {
        boolean result = customerService.customerDelete(id);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/detail/{id}")
    public CustomerDto detail(@PathVariable("id") int id) {
        return customerService.detail(id);
    }
}
