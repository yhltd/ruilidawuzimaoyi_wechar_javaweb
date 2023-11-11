package com.myboot.controller;

import com.myboot.pojo.Invoicing;
import com.myboot.service.InvoicingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoicing")
public class InvoicingController {
    @Autowired
    private InvoicingService service;

    @GetMapping("/getall")
    public List<Invoicing> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public String invAdd(@RequestBody Invoicing invoicing){
        boolean res = service.invoicingAdd(invoicing);
        if (res){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PutMapping("/update")
    public String invoicingUpd(@RequestBody Invoicing invoicing){
        boolean res = service.invoicingUpd(invoicing);
        if (res){
            return "success";
        }
        else {
            return "error";
        }
    }

    @DeleteMapping("/delete/{iId}")
    public String invoicingDel(@PathVariable("iId") Integer id){
        boolean res = service.invoicingDel(id);
        if (res){
            return "success";
        }
        else {
            return "error";
        }
    }
}
