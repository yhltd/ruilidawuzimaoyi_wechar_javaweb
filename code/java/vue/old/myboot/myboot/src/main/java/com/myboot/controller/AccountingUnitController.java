package com.myboot.controller;

import com.myboot.pojo.AccountingUnit;
import com.myboot.service.AccountingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")

public class AccountingUnitController {
    @Autowired
    private AccountingUnitService service;

    @GetMapping("/getall")
    public List<AccountingUnit> getAll(){
        return service.getAll();
    }

    @PostMapping("/add/{aName}")
    public String accountAdd(@PathVariable("aName") String aName){
        boolean result = service.accountAdd(aName);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @PutMapping("/update/{aName}/{aId}")
    public String accountUpdate(@PathVariable("aName") String aName,@PathVariable("aId") Integer aId){
        boolean result = service.accountUpdate(aName,aId);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }

    @DeleteMapping("/delete/{aId}")
    public String accountDelete(@PathVariable("aId") Integer id){
        boolean result = service.accountDelete(id);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }
}
