package com.myboot.service;


import com.myboot.dto.CustomerDto;
import com.myboot.dto.CustomerSearch;
import com.myboot.dto.ProdectSearch;
import com.myboot.dto.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getProduct(ProdectSearch prodectSearch);

    public boolean saveProduct(ProductDto productDto);

    boolean updateProduct(ProductDto productDto);

    public boolean deleteProduct(int id);

    ProductDto detail(int id);
}
