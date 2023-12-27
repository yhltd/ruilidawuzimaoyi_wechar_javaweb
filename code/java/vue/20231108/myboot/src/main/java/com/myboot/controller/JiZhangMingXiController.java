package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.JiZhangMingXiService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jiZhangMingXi")
public class JiZhangMingXiController {
    @Autowired
    private JiZhangMingXiService service;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }


    @PostMapping("/updateById")
    public String updateById(HttpSession session,@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("body");
        JiZhangMingXi jiZhangMingXi = data.getObject("head", JiZhangMingXi.class);
        Integer this_id = service.updateById(jiZhangMingXi);
        return ResponseCommon.success(this_id);
    }


}
