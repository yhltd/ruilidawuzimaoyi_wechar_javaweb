package com.myboot.dto;

import com.myboot.pojo.Customer;
import com.myboot.pojo.CustomerItem;
import lombok.Data;
import lombok.Setter;

import java.util.List;
@Data
@Setter
public class CustomerDto extends Customer {

    private List<CustomerItem> customerItemList;

}
