package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CustomerItemService;
import com.myboot.service.CustomerService;
import com.myboot.service.PeiZhiShuiLvService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/peiZhiShuiLv")
public class PeiZhiShuiLvController {
    @Autowired
    private PeiZhiShuiLvService service;


    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/update")
    public String update(@RequestBody PeiZhiShuiLv peiZhiShuiLv) {
        Integer result = service.update(peiZhiShuiLv);
        return ResponseCommon.success(peiZhiShuiLv);
    }


}
