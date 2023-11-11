package com.myboot.controller;

import com.myboot.pojo.Receiving;
import com.myboot.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receiving")
public class ReceivingController {
    @Autowired
    private ReceivingService service;

    @GetMapping("/getall")
    public List<Receiving> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public String receAdd(@RequestBody Receiving receiving){
        boolean result = service.receAdd(receiving);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String receUpdate(@RequestBody Receiving receiving){
        boolean result = service.receUpdate(receiving);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @DeleteMapping("/del/{rId}")
    public String receDel(@PathVariable("rId") Integer id){
     boolean result = service.receDel(id);
     if (result){
         return "success";
     }
     else {
         return "error";
     }
    }
}
