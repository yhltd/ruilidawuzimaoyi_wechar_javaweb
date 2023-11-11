package com.myboot.dto;


import com.myboot.pojo.Product;
import lombok.Data;

@Data
public class ProdectSearch extends Product {

    private Integer currentPage;


    private Integer pageSize;

    private String enable;

}
