package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.PeiZhiGuiGe;
import com.myboot.pojo.Product;
import com.myboot.service.PeiZhiGuiGeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/peiZhiGuiGe")
public class PeiZhiGuiGeController {
    @Autowired
    private PeiZhiGuiGeService service;

    @GetMapping("/getAll")
    public String getAll(){return ResponseCommon.success(service.getAll());}

    @PostMapping("/queryProduct")
    public String selectByProduct(@RequestBody @NonNull JSONObject data){
        String type = data.getString("type");
        String guige = data.getString("guige");
        List<PeiZhiGuiGe> peiZhiGuiGe = service.selectByProduct(type,guige);
        return ResponseCommon.success(peiZhiGuiGe);
    }
    @PostMapping("/peiZhiGuiGeAdd")
    public String peiZhiGuiGeAdd(@RequestBody PeiZhiGuiGe peiZhiGuiGe){
        Integer result = service.peiZhiGuiGeAdd(peiZhiGuiGe);
        return ResponseCommon.success(peiZhiGuiGe);
    }

    @PostMapping("/peiZhiGuiGeUpd")
    public String peiZhiGuiGeUp(@RequestBody PeiZhiGuiGe peiZhiGuiGe){
        Integer result = service.peiZhiGuiGeUpd(peiZhiGuiGe);
        return  ResponseCommon.success(peiZhiGuiGe);
    }

    @RequestMapping("/delPeiZhiGuiGe")
    public String delPeiZhiGuiGe(@RequestBody JSONObject data){
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.delPeiZhiGuiGe(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }


}
