package com.myboot.controller;

import com.myboot.pojo.User;
import com.myboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/getall")
    public List<User> getAll(){
        return service.gatAll();
    }

    @PostMapping("/useradd")
    public String addUser(User user){
        boolean result = service.insertUser(user);
        if (result){
            return "success";
        }else {
            return "error";
        }
    }

}
