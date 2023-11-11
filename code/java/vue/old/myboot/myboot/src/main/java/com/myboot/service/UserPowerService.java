package com.myboot.service;

import com.myboot.pojo.UserPower;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/7 17:27
 */
public interface UserPowerService {
    /**
     * 获取全部权限名以及权限id
     * @return
     */
    List<UserPower> getUserPowerIdAndName();
}
