package com.myboot.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.myboot.pojo.Goods;
import com.myboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService service;

    @GetMapping("/getall")
    public List<Goods> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public String goodsAdd(@RequestBody Goods goods){
        boolean result = service.goodsAdd(goods);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String goodsUpdate(@RequestBody Goods goods){
        boolean result = service.goodsUpdate(goods);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @DeleteMapping("/del/{gId}")
    public String goodsDel(@PathVariable("gId") Integer id){
        boolean result = service.goodsDel(id);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }
}
