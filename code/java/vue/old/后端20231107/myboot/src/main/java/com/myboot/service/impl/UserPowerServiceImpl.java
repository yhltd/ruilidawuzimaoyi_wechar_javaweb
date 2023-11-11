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
    public List<UserPower> getUserPowerIdAndName() {
        return userPowerMapper.getUserPowerIdAndName();
    }
}
