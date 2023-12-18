package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.User;
import com.myboot.pojo.UserPower;
import com.myboot.pojo.User_UserPower;
import com.myboot.service.UserPowerService;
import com.myboot.service.UserService;
import com.myboot.util.GsonUtil;
import com.myboot.util.SessionUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserPowerService powerService;

    @PostMapping("/getLogin")
    public String getLogin(HttpSession session) {
        String user_str = SessionUtil.getToken(session);
        String power_str = SessionUtil.getPower(session);
        User user = GsonUtil.toEntity(SessionUtil.getToken(session), User.class);
        UserPower userPower = GsonUtil.toEntity(SessionUtil.getPower(session), UserPower.class);
        User_UserPower user_UserPower = new User_UserPower(user, userPower);
        return ResponseCommon.success(user_UserPower);
    }

    @GetMapping("/getall")
    public List<User> getAll() {
        return service.gatAll();
    }

    @PostMapping("/useradd")
    public String addUser(@RequestBody User user) {
        boolean result = service.insertUser(user);
        return result ? ResponseCommon.success() : ResponseCommon.failed(ResponseErrorCode.INS_FAILED);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/queryUserInfoById")
    public String getUserInfo(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }

        User user = service.getUserInfoById(data.getInteger("id"));
        if (user == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该用户");
        }
        return ResponseCommon.success(user);
    }

    @PostMapping("/updUsrInfo")
    public String updateUserById(@RequestBody User user) {
        boolean res = service.updateById(user);
        return res ? ResponseCommon.success() : ResponseCommon.failed(ResponseErrorCode.UPD_FAILED);
    }

    /**
     * 查询用户
     * <p>
     *
     * @return
     */
    @PostMapping("/queryAllUsers")
    public String queryUsers() {
        List<User> users = service.queryAllUsers();
        return ResponseCommon.success(users);
    }

    @PostMapping("/queryShenpis")
    public String queryShenpis() {
        List<User> users = service.queryShenpis();
        return ResponseCommon.success(users);
    }

    @RequestMapping("/delUsers")
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

    @PostMapping("/fuzzyQuery")
    public String fuzzyQuery(@RequestBody @NonNull JSONObject data) {
        String keyword = data.getString("keyword");
        if(keyword == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<User> users = service.fuzzyQuery(keyword);

        return ResponseCommon.success(users);
    }

    @PostMapping("/updateMoRen")
    public String updateMoRen(@RequestBody JSONObject data) {
        String type = data.getString("type");
        String typeId = data.getString("typeId");
        Integer id = data.getInteger("id");
        boolean res = service.updateMoRen(type,typeId,id);
        return res ? ResponseCommon.success() : ResponseCommon.failed(ResponseErrorCode.UPD_FAILED);
    }
}
