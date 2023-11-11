package com.myboot.mapper;

import com.myboot.pojo.UserPower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/6 11:02
 */
@Mapper
public interface UserPowerMapper {
    /**
     * 查询用户权限信息
     * @param powerName 权限名称
     * @return
     */
    UserPower getUserPowerByName(@Param("name") String powerName);

    /**
     * 获取全部权限id以及name
     * @return
     */
    UserPower queryUserPowerById(Integer id);

    /**
     * 获取全部权限id以及name
     * @return
     */
    List<UserPower> queryPower(@Param("name") String name);


    int powerAdd(UserPower userPower);


    int powerUpd(UserPower userPower);


    int powerDelById(Integer id);

}
