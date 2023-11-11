package com.myboot.dto;


import com.myboot.pojo.Customer;
import lombok.Data;

@Data
public class CustomerSearch extends Customer {

    private Integer currentPage;


    private Integer pageSize;

}
