package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.XiaoShouKaiPiao;
import com.myboot.service.CaiGouShouPiaoService;
import com.myboot.service.XiaoShouKaiPiaoService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shouPiao")
public class CaiGouShouPiaoController {
    @Autowired
    private CaiGouShouPiaoService service;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String kaipiao_danwei = data.getString("kaipiao_danwei");
        String shoupiao_danwei = data.getString("shoupiao_danwei");
        String shoupiao_zhuangtai = data.getString("shoupiao_zhuangtai");
        List<CaiGouShouPiao> caiGouShouPiao = service.queryList(start_date,stop_date,shoupiao_danwei,kaipiao_danwei,shoupiao_zhuangtai);
        return ResponseCommon.success(caiGouShouPiao);
    }

    @PostMapping("/getShouPiao")
    public String getKaiPiao(@RequestBody @NonNull JSONObject data) {
        String xinxi_tuisong = data.getString("xinxi_tuisong");
        List<CaiGouShouPiao> caiGouShouPiao = service.getAllByShenHe(xinxi_tuisong);
        return ResponseCommon.success(caiGouShouPiao);
    }

    @PostMapping("/shouPiaoAdd")
    public String shouPiaoAdd(@RequestBody CaiGouShouPiao caiGouShouPiao) {
        Integer result = service.caiGouShouPiaoAdd(caiGouShouPiao);
        return ResponseCommon.success(caiGouShouPiao);
    }

    @PostMapping("/shouPiaoUpd")
    public String shouPiaoUpd(@RequestBody CaiGouShouPiao caiGouShouPiao) {
        Integer result = service.caiGouShouPiaoUpd(caiGouShouPiao);
        return ResponseCommon.success(caiGouShouPiao);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectByCaiGouId")
    public String selectByCaiGouId(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }

        List<CaiGouShouPiao> caiGouShouPiao = service.selectByShouPiaoId(data.getInteger("id"));
        if (caiGouShouPiao == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该用户");
        }
        return ResponseCommon.success(caiGouShouPiao);
    }

    @RequestMapping("/delShouPiao")
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


}
