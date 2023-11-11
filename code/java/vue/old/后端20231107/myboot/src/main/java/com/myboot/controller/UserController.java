package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.User;
import com.myboot.service.UserService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/getall")
    public List<User> getAll() {
        return service.gatAll();
    }

    @PostMapping("/useradd")
    public String addUser(User user) {
        boolean result = service.insertUser(user);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/queryUserInfo")
    public String getUserInfo(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }

        User user = service.getUserInfoById(data.getInteger("id"));
        if (user == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该用户");
        }
        return ResponseCommon.success(user);
    }

    @PostMapping("/updUsrInfo")
    public String updateUserById(@RequestBody User user) {
        boolean res = service.updateById(user);
        return ResponseCommon.success();
    }

    /**
     * 模糊查询用户
     * <p>
     * 查询的用户信息字段为 name power shenpi
     *
     * @return
     */
    @PostMapping("/queryUsers")
    public String queryUsers() {
        List<User> users = service.queryAllUsers();
        return ResponseCommon.success(users);
    }
}
