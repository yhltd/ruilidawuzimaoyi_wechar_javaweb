package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.KuCun;
import com.myboot.pojo.ZhangHuYuE;
import com.myboot.service.KuCunService;
import com.myboot.service.ZhangHuYuEService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zhangHuYuE")
public class ZhangHuYuEController {
    @Autowired
    private ZhangHuYuEService service;

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String riqi = data.getString("riqi");
        if(riqi == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<ZhangHuYuE> zhangHuYuE = service.queryList(riqi);

        return ResponseCommon.success(zhangHuYuE);
    }
}
