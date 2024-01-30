package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.QiChuGongYingShang;
import com.myboot.pojo.QiChuKeHu;
import com.myboot.service.JiZhangService;
import com.myboot.service.QiChuGongYingShangService;
import com.myboot.service.QiChuKeHuService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/qiChuGongYingShang")
public class QiChuGongYingShangController {
    @Autowired
    private QiChuGongYingShangService service;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/add")
    public String add(@RequestBody QiChuGongYingShang qiChuGongYingShang) {
        Integer this_id = service.add(qiChuGongYingShang);
        return ResponseCommon.success(qiChuGongYingShang);
    }

    @PostMapping("/upd")
    public String upd(@RequestBody QiChuGongYingShang qiChuGongYingShang) {
        Integer this_id = service.upd(qiChuGongYingShang);
        return ResponseCommon.success(qiChuGongYingShang);
    }

    @RequestMapping("/del")
    public String del(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.del(list);
        StringBuffer sb = new StringBuffer();
        if(list.size() - res > 0){
            sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        }else{
            sb.append("成功删除了 ").append(res).append(" 条数据");
        }
        return ResponseCommon.success(sb.toString());
    }
}
