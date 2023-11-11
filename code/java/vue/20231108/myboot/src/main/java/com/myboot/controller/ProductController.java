package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CustomerItemService;
import com.myboot.service.CustomerService;
import com.myboot.service.ProductItemService;
import com.myboot.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductItemService itemService;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @GetMapping("/selectCaiGouProduct")
    public String selectCaiGouProduct() {
        return ResponseCommon.success(service.selectCaiGouProduct());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String name = data.getString("name");
        String type = data.getString("type");
        String enable = data.getString("enable");
        List<Product> products = service.queryList(name,type,enable);
        return ResponseCommon.success(products);
    }

    @PostMapping("/productAdd")
    public String productAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        Product product = data.getObject("head", Product.class);
        List<ProductItem> productItems = body.toJavaList(ProductItem.class);
        Product dingDan = new Product();
        BeanUtils.copyProperties(product, dingDan);
        Integer this_id = service.productAdd(dingDan);
        for(int i=0; i<productItems.size(); i++){
            productItems.get(i).setProductId(String.valueOf(dingDan.getId()));
            int result2 = itemService.itemAdd(productItems.get(i));
        }
        return ResponseCommon.success(product);
    }

    @PostMapping("/productUpd")
    public String productUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        Product product = data.getObject("head", Product.class);
        List<ProductItem> productItems = body.toJavaList(ProductItem.class);
        Product dingDan = new Product();
        BeanUtils.copyProperties(product, dingDan);
        Integer this_id = service.productUpd(dingDan);
        itemService.itemDelById(dingDan.getId());
        for(int i=0; i<productItems.size(); i++){
            productItems.get(i).setProductId(String.valueOf(dingDan.getId()));
            int result2 = itemService.itemAdd(productItems.get(i));
        }
        return ResponseCommon.success(product);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectById")
    public String selectById(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        Integer id = data.getInteger("id");
        List<Product> product = service.selectById(data.getInteger("id"));
        ProductAdd productAdd = new ProductAdd();
        productAdd.setId(product.get(0).getId());
        productAdd.setName(product.get(0).getName());
        productAdd.setType(product.get(0).getType());
        productAdd.setDanwei(product.get(0).getDanwei());
        productAdd.setCaizhi(product.get(0).getCaizhi());
        productAdd.setJishuBiaozhun(product.get(0).getJishuBiaozhun());
        productAdd.setBeizhu(product.get(0).getBeizhu());
        productAdd.setItemId(product.get(0).getItemId());
        productAdd.setProductId(product.get(0).getProductId());
        productAdd.setGuige(product.get(0).getGuige());
        productAdd.setBianhao(product.get(0).getBianhao());
        productAdd.setLingshouPrice(product.get(0).getLingshouPrice());
        productAdd.setLingshouBili(product.get(0).getLingshouBlli());
        productAdd.setPifaPrice(product.get(0).getPifaPrice());
        productAdd.setPifaBili(product.get(0).getPifaBili());
        productAdd.setDakehuPrice(product.get(0).getDakehuPirce());
        productAdd.setDakehuBili(product.get(0).getDakehuBili());
        productAdd.setCaigouPrice(product.get(0).getCaigouPrice());
        productAdd.setJinxiang(product.get(0).getJinxiang());
        productAdd.setXiaoxiang(product.get(0).getXiaoxiang());
        productAdd.setEnable(product.get(0).getEnable());
        productAdd.setItemList(itemService.selectByProductId(data.getInteger("id")));

        if (product == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(productAdd);
    }

    @RequestMapping("/delProduct")
    public String delProduct(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.deleteById(list);
        StringBuffer sb = new StringBuffer();
        if(list.size() - res > 0){
            sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        }else{
            sb.append("成功删除了 ").append(res).append(" 条数据");
        }
        return ResponseCommon.success(sb.toString());
    }


}
