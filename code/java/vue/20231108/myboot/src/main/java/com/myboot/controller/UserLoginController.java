package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.User_UserPower;
import com.myboot.service.UserService;
import com.myboot.util.SessionUtil;
import com.myboot.util.StringUtils;
import com.myboot.vo.UserVO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录、登出控制器
 * <p>
 * requestMapping 中接口为 V零
 * @author hui
 * @date 2023/11/6 10:01
 */
@RestController
@CrossOrigin
@RequestMapping("/V0")
public class UserLoginController {
    @Autowired
    private UserService userService;
    /**
     * 登录
     * @param userVO
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session, @RequestBody @NonNull UserVO userVO) {
        User_UserPower u = userService.login(userVO);
        SessionUtil.remove(session, "token");
        SessionUtil.remove(session, "power");
        if(u == null) {
            // 账密错误执行
            return ResponseCommon.failed(ResponseErrorCode.LOGIN_FAILED);
        } else if (u.getUserPower() == null) {
            // 用户无权限执行
            return ResponseCommon.failed(ResponseErrorCode.NO_PERMISSION);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", u.getUser());
        map.put("power",u.getUserPower());
        SessionUtil.setToken(session, map.get("token").toString());
        SessionUtil.setPower(session, map.get("power").toString());
        JSONObject data = new JSONObject();
        data.put("user", u.getUser());
        data.put("userPower", u.getUserPower());
        return ResponseCommon.success(data);
    }

    /**
     * 登出
     * @return
     */
    public String logout() {
        return null;
    }
}
