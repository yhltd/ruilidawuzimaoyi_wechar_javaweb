package com.myboot.controller;

import com.myboot.pojo.ContactPerson;
import com.myboot.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactPersonController {

    @Autowired
    private ContactPersonService service;

    @GetMapping("/getall")
    public List<ContactPerson> getAll(){
        return service.getAll();
    }

    @PostMapping("/add")
    public String contactAdd(@RequestBody ContactPerson contactPerson){
        boolean result = service.contactAdd(contactPerson);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }
    @PutMapping("/update")
    public String contactUpdate(ContactPerson contactPerson){
        boolean result = service.contactUpdate(contactPerson);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }
    @DeleteMapping("/delete/{cpId}")
    public String contactDel(@PathVariable("cpId") Integer id){
        boolean result = service.contactDel(id);
        if (result){
            return "success";
        }
        else {
            return "error";
        }
    }
}
