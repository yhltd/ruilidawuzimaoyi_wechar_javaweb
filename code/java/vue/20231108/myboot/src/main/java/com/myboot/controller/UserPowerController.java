package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.Peizhi;
import com.myboot.pojo.UserPower;
import com.myboot.service.UserPowerService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hui
 * @date 2023/11/7 17:26
 */
@RestController
@CrossOrigin
@RequestMapping("/userpower")
public class UserPowerController {
    @Resource
    private UserPowerService powerService;

    @PostMapping("/queryUserPowerById")
    public String queryUserPowerById(@RequestBody @NonNull JSONObject data) {
        Integer id = data.getInteger("id");
        UserPower powers = powerService.queryUserPowerById(id);
        return ResponseCommon.success(powers);
    }

    @PostMapping("/queryPower")
    public String queryPower(@RequestBody @NonNull JSONObject data) {
        String name = data.getString("name");
        List<UserPower> powers = powerService.queryPower(name);
        return ResponseCommon.success(powers);
    }

    @PostMapping("/getUserPowerByName")
    public String getUserPowerByName(@RequestBody @NonNull JSONObject data) {
        String name = data.getString("name");
        UserPower powers = powerService.getUserPowerByName(name);
        return ResponseCommon.success(powers);
    }

    @PostMapping("/powerAdd")
    public String powerAdd(@RequestBody UserPower userPower) {
        Integer result = powerService.powerAdd(userPower);
        return ResponseCommon.success(userPower);
    }

    @PostMapping("/powerUpd")
    public String powerUpd(@RequestBody UserPower userPower) {
        Integer result = powerService.powerUpd(userPower);
        return ResponseCommon.success(userPower);
    }

    @RequestMapping("/delPower")
    public String delPower(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = powerService.powerDelById(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }
}
