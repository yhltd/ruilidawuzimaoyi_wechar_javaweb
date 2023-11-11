package com.myboot.controller;

import com.myboot.dto.ProdectSearch;
import com.myboot.dto.ProductDto;
import com.myboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
@ResponseBody
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public List<ProductDto> getProduct(@RequestBody ProdectSearch prodectSearch) {
        return productService.getProduct(prodectSearch);
    }

    @PostMapping("/add")
    public String saveProduct(@RequestBody ProductDto productDto) {
        boolean result = productService.saveProduct(productDto);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody ProductDto productDto) {
        boolean result = productService.updateProduct(productDto);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        boolean result = productService.deleteProduct(id);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/detail/{id}")
    public ProductDto detail(@PathVariable("id") int id) {
        return productService.detail(id);
    }
}
