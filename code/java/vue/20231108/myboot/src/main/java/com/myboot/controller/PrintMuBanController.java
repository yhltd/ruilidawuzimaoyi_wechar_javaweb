package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.PrintMuBanService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/printMuBan")
public class PrintMuBanController {
    @Autowired
    private PrintMuBanService service;

    @GetMapping("/getAll")
    public String getAll(HttpSession session) {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(HttpSession session,@RequestBody @NonNull JSONObject data) {
        String name = data.getString("name");
        List<PrintMuBan> printMuBans = service.queryList(name);
        return ResponseCommon.success(printMuBans);
    }

    @PostMapping("/getMuBanById")
    public String getMuBanById(HttpSession session,@RequestBody @NonNull JSONObject data) {
        Integer id = data.getInteger("id");
        List<PrintMuBan> printMuBans = service.getMuBanById(id);
        return ResponseCommon.success(printMuBans);
    }

    @PostMapping("/muBanAdd")
    public String muBanAdd(HttpSession session,@RequestBody PrintMuBan printMuBan) {
        Integer result = service.muBanAdd(printMuBan);
        return ResponseCommon.success(printMuBan);
    }

    @PostMapping("/muBanUpd")
    public String muBanUpd(HttpSession session,@RequestBody PrintMuBan printMuBan) {
        Integer result = service.muBanUpd(printMuBan);
        return ResponseCommon.success(printMuBan);
    }

    @RequestMapping("/delById")
    public String delById(HttpSession session,@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.delById(list);
        StringBuffer sb = new StringBuffer();
        if(list.size() - res > 0){
            sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        }else{
            sb.append("成功删除了 ").append(res).append(" 条数据");
        }
        return ResponseCommon.success(sb.toString());
    }


}
