package com.myboot.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.myboot.pojo.Details;
import com.myboot.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    private DetailsService service;

    @GetMapping("/getall")
    public List<Details> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public String detailsAdd(@RequestBody Details details){
        boolean res = service.detailsAdd(details);
        if (res){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String detailsUpd(@RequestBody Details details){
        boolean res = service.detailsUpdate(details);
        if (res){
            return "success";
        }
        else {
            return "error";
        }
    }

    @DeleteMapping("/delete/{dId}")
    public String detailsDel(@PathVariable("dId") Integer id){
        boolean res = service.detailsDel(id);
        if (res){
            return "success";
        }
        else {
            return "error";
        }
    }
}
