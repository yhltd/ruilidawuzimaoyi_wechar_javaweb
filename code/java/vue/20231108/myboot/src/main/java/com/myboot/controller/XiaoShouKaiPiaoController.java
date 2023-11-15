package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.User;
import com.myboot.pojo.XiaoShouKaiPiao;
import com.myboot.service.UserService;
import com.myboot.service.XiaoShouKaiPiaoService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kaiPiao")
public class XiaoShouKaiPiaoController {
    @Autowired
    private XiaoShouKaiPiaoService service;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String shoupiao_danwei = data.getString("shoupiao_danwei");
        String kaipiao_danwei = data.getString("kaipiao_danwei");
        String kaipiao_zhuangtai = data.getString("kaipiao_zhuangtai");
        List<XiaoShouKaiPiao> xiaoShouKaiPiao = service.queryList(start_date,stop_date,shoupiao_danwei,kaipiao_danwei,kaipiao_zhuangtai);
        return ResponseCommon.success(xiaoShouKaiPiao);
    }

    @PostMapping("/getKaiPiao")
    public String getKaiPiao(@RequestBody @NonNull JSONObject data) {
        String xinxi_tuisong = data.getString("xinxi_tuisong");
        List<XiaoShouKaiPiao> xiaoShouKaiPiao = service.getAllByShenHe(xinxi_tuisong);
        return ResponseCommon.success(xiaoShouKaiPiao);
    }

    @PostMapping("/kaiPiaoAdd")
    public String kaiPiaoAdd(@RequestBody XiaoShouKaiPiao xiaoShouKaiPiao) {
        service.kaiPiaoAdd(xiaoShouKaiPiao);
        return ResponseCommon.success(xiaoShouKaiPiao);
    }

    @PostMapping("/kaiPiaoUpd")
    public String kaiPiaoUpd(@RequestBody XiaoShouKaiPiao xiaoShouKaiPiao) {
        service.kaiPiaoUpd(xiaoShouKaiPiao);
        return ResponseCommon.success(xiaoShouKaiPiao);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectXiaoShouById")
    public String selectXiaoShouById(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }

        List<XiaoShouKaiPiao> xiaoShouKaiPiao = service.selectXiaoShouById(data.getInteger("id"));
        if (xiaoShouKaiPiao == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该用户");
        }
        return ResponseCommon.success(xiaoShouKaiPiao);
    }

    @RequestMapping("/delKaiPiao")
    public String delUsers(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.delUsers(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }

    @RequestMapping("/kaiPiao")
    public String kaiPiao(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.kaiPiao(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功开票了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条开票失败");
        return ResponseCommon.success(sb.toString());
    }

    @PostMapping("/selectYingKai")
    public String selectYingKai(@RequestBody @NonNull JSONObject data) {
        List<XiaoShouKaiPiao> xiaoShouKaiPiao = service.selectYingKai();
        return ResponseCommon.success(xiaoShouKaiPiao);
    }


}
