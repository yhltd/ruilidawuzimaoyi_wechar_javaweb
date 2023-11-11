package com.myboot.service.impl;

import com.myboot.mapper.UserPowerMapper;
import com.myboot.pojo.UserPower;
import com.myboot.service.UserPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/7 17:30
 */
@Service
public class UserPowerServiceImpl implements UserPowerService {
    @Autowired
    private UserPowerMapper userPowerMapper;

    @Override
    public UserPower getUserPowerByName(String powerName) {
        return userPowerMapper.getUserPowerByName(powerName);
    }

    @Override
    public UserPower queryUserPowerById(Integer id) {
        return userPowerMapper.queryUserPowerById(id);
    }

    @Override
    public List<UserPower> queryPower(String name) {
        return userPowerMapper.queryPower(name);
    }

    @Override
    public int powerAdd(UserPower userPower) {
        return userPowerMapper.powerAdd(userPower);
    }

    @Override
    public int powerUpd(UserPower userPower) {
        return userPowerMapper.powerUpd(userPower);
    }

    @Override
    public int powerDelById(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += userPowerMapper.powerDelById(id);
        }
        return count;
    }


}
