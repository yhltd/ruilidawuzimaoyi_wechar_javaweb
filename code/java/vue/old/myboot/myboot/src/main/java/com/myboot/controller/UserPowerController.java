package com.myboot.controller;

import com.myboot.common.ResponseCommon;
import com.myboot.pojo.UserPower;
import com.myboot.service.UserPowerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hui
 * @date 2023/11/7 17:26
 */
@RestController
@CrossOrigin
@RequestMapping("/power")
public class UserPowerController {
    @Resource
    private UserPowerService powerService;

    @PostMapping("/getUserPowerIdAndName")
    public String getUserPowerIdAndName() {
        List<UserPower> powers = powerService.getUserPowerIdAndName();
        return ResponseCommon.success(powers);
    }
}
