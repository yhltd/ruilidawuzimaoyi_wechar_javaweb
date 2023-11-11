package com.myboot.controller;

import com.myboot.pojo.Customer;
import com.myboot.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@ResponseBody
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("getall")
    public List<Customer> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public String customerAdd(@RequestBody Customer customer){
        boolean result = service.customerAdd(customer);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String customerUpdate(@RequestBody Customer customer){
        boolean result = service.customerUpdate(customer);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @DeleteMapping("/delete/{cId}")
    public String customerDelete(@PathVariable("cId") String id){
        boolean result = service.customerDelete(id);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }
}
