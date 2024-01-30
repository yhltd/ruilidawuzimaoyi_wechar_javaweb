package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.XiaoShouKaiPiaoTongJi;
import com.myboot.service.JiZhangService;
import com.myboot.service.XiaoShouKaiPiaoTongJiService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jiZhang")
public class XiaoShouKaiPiaoTongJiController {
    @Autowired
    private XiaoShouKaiPiaoTongJiService service;

    @PostMapping("/getTongJiByDanWei")
    public String getTongJiByDanWei(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<XiaoShouKaiPiaoTongJi> xiaoShouKaiPiaoTongJi = service.getTongJiByDanWei(start_date,stop_date);
        return ResponseCommon.success(xiaoShouKaiPiaoTongJi);
    }

    @PostMapping("/getTongJiByKeHu")
    public String getTongJiByKeHu(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<XiaoShouKaiPiaoTongJi> xiaoShouKaiPiaoTongJi = service.getTongJiByKeHu(start_date,stop_date);
        return ResponseCommon.success(xiaoShouKaiPiaoTongJi);
    }
}
