package com.myboot.service.impl;

import com.myboot.dto.CustomerDto;
import com.myboot.dto.ProdectSearch;
import com.myboot.dto.ProductDto;
import com.myboot.mapper.ProductItemMapper;
import com.myboot.mapper.ProductMapper;
import com.myboot.pojo.*;
import com.myboot.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductItemMapper productItemMapper;

    @Override
    public List<ProductDto> getProduct(ProdectSearch prodectSearch) {
        ProductExample example = getExample(prodectSearch);
        List<Product> productList = productMapper.selectByExample(example);
        List<String> prodectIdList = productList.stream().map(s -> String.valueOf(s.getId())).collect(Collectors.toList());
        ProductItemExample productItemExample = new ProductItemExample();
        productItemExample.createCriteria().andProductIdIn(prodectIdList);
        List<ProductItem> productItemList;
        productItemList = productItemMapper.selectByExample(productItemExample);
        if (prodectSearch.getEnable() != null) {
            productItemList = productItemList.stream().filter(s -> s.getEnable().equals(prodectSearch.getEnable())).collect(Collectors.toList());
        }
        Map<String, List<ProductItem>> productItemMap = new HashMap<>();
        for (ProductItem productItem : productItemList) {
            productItemMap.computeIfAbsent(productItem.getProductId(), k -> new ArrayList<ProductItem>()).add(productItem);
        }
        List<ProductDto> result = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            List<ProductItem> itemList = productItemMap.get(String.valueOf(product.getId()));
            productDto.setProductItemList(itemList);
            result.add(productDto);
        }
        return result;
    }

    @Override
    public boolean saveProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productMapper.insert(product);
        for (ProductItem productItem : productDto.getProductItemList()) {
            productItem.setProductId(String.valueOf(product.getId()));
            productItemMapper.insert(productItem);
        }
        return true;
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productMapper.updateByPrimaryKey(product);
        for (ProductItem productItem : productDto.getProductItemList()) {
            productItemMapper.updateByPrimaryKey(productItem);
        }
        return true;
    }

    @Override
    public boolean deleteProduct(int id) {
        productMapper.deleteByPrimaryKey(id);
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andProductIdEqualTo(String.valueOf(id));
        productItemMapper.deleteByExample(example);
        return true;
    }

    @Override
    public ProductDto detail(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andProductIdEqualTo(String.valueOf(id));
        List<ProductItem> productItemList = productItemMapper.selectByExample(example);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        productDto.setProductItemList(productItemList);
        return productDto;
    }

    private ProductExample getExample(ProdectSearch prodectSearch) {
        ProductExample example = new ProductExample();
        example.setOffset((prodectSearch.getCurrentPage() - 1L) * prodectSearch.getPageSize());
        example.setLimit(prodectSearch.getPageSize());
        if (prodectSearch.getName() != null) {
            example.createCriteria().andNameLike("%" + prodectSearch.getName() + "%");
        }
        if (prodectSearch.getType() != null) {
            example.createCriteria().andTypeEqualTo(prodectSearch.getType());
        }
        return example;

    }
}
