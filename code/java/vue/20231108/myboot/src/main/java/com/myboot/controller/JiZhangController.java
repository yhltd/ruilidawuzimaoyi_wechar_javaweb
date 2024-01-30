package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.KuCun;
import com.myboot.service.JiZhangService;
import com.myboot.service.KuCunService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jiZhang")
public class JiZhangController {
    @Autowired
    private JiZhangService service;

    @PostMapping("/getJiZhang")
    public String getJiZhang(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String dianpu = data.getString("dianpu");
        String jizhang_danwei = data.getString("jizhang_danwei");
        String jizhang_zhanghu = data.getString("jizhang_zhanghu");
        List<JiZhang> jiZhang = service.getJiZhang(start_date,stop_date,dianpu,jizhang_danwei,jizhang_zhanghu);
        return ResponseCommon.success(jiZhang);
    }
}
