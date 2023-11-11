package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.XiaoShouKaiPiao;
import com.myboot.pojo.ZhuanZhang;
import com.myboot.service.XiaoShouKaiPiaoService;
import com.myboot.service.ZhuanZhangService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/zhuanZhang")
public class ZhuanZhangController {
    @Autowired
    private ZhuanZhangService service;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String zhuanru = data.getString("zhuanru");
        String zhuanchu = data.getString("zhuanchu");
        List<ZhuanZhang> zhuanZhangs = service.queryList(start_date,stop_date,zhuanru,zhuanchu);
        return ResponseCommon.success(zhuanZhangs);
    }

    @PostMapping("/zhuanZhangAdd")
    public String zhuanZhangAdd(@RequestBody ZhuanZhang zhuanZhang) {
        service.zhuanZhangAdd(zhuanZhang);
        return ResponseCommon.success(zhuanZhang);
    }

    @PostMapping("/zhuanZhangUpd")
    public String zhuanZhangUpd(@RequestBody ZhuanZhang zhuanZhang) {
        service.zhuanZhangUpd(zhuanZhang);
        return ResponseCommon.success(zhuanZhang);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectZhuanZhangById")
    public String selectZhuanZhangById(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }

        List<ZhuanZhang> zhuanZhangs = service.selectZhuanZhangById(data.getInteger("id"));
        if (zhuanZhangs == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该用户");
        }
        return ResponseCommon.success(zhuanZhangs);
    }

    @RequestMapping("/delZhuanZhang")
    public String delZhuanZhang(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.delUsers(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }


}
