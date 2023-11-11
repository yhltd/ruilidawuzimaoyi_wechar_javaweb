package com.myboot.service;

import com.myboot.pojo.UserPower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/7 17:27
 */
public interface UserPowerService {

    /**
     * 查询用户权限信息
     * @param powerName 权限名称
     * @return
     */
    UserPower getUserPowerByName(String powerName);

    /**
     * 获取全部权限名以及权限id
     * @return
     */
    UserPower queryUserPowerById(Integer id);

    /**
     * 条件查询
     * @return
     */
    List<UserPower> queryPower(String name);


    int powerAdd(UserPower userPower);


    int powerUpd(UserPower userPower);


    int powerDelById(List<Integer> list);
}
