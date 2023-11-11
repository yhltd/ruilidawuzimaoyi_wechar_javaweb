package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.KuCun;
import com.myboot.pojo.User;
import com.myboot.service.KuCunService;
import com.myboot.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kuCun")
public class KuCunController {
    @Autowired
    private KuCunService service;

    @GetMapping("/getKuCun")
    public String getKuCun() {
        return ResponseCommon.success(service.getKuCun());
    }


    @PostMapping("/getKuCunByCangKu")
    public String getKuCunByCangKu(@RequestBody @NonNull JSONObject data) {
        String cangku = data.getString("cangku");
        if(cangku == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<KuCun> kuCun = service.getKuCunByCangKu(cangku);

        return ResponseCommon.success(kuCun);
    }
}
