package com.myboot.dto;

import com.myboot.pojo.Product;
import com.myboot.pojo.ProductItem;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class ProductDto extends Product {

    private List<ProductItem> productItemList;

}
