package com.myboot.service.impl;

import com.myboot.mapper.UserMapper;
import com.myboot.mapper.UserPowerMapper;
import com.myboot.pojo.User;
import com.myboot.pojo.UserPower;
import com.myboot.pojo.User_UserPower;
import com.myboot.service.UserService;
import com.myboot.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Resource
    private UserPowerMapper userPowerMapper;

    @Override
    public List<User> gatAll() {
        List<User> users = mapper.getAll();
        return users;
    }

    @Override
    public boolean insertUser(User user) {
        boolean flag = false;
        int result = mapper.insertUser(user);
        if (result == 1){
            flag = true;
            return flag;
        }else {
            return flag;
        }

    }

    @Override
    public User_UserPower login(UserVO userVO) {
        User user = mapper.login(userVO);
        if(user == null) {
            // 账密错误执行以下代码
            return null;
        }

        // 查询用户权限
        UserPower userPower = userPowerMapper.getUserPowerByName(user.getPower());
        return new User_UserPower(user, userPower);
    }

    @Override
    public User getUserInfoById(Integer id) {
        return mapper.getUserInfoById(id);
    }

    @Override
    public boolean updateById(User user) {
        return mapper.updateById(user);
    }

    @Override
    public List<User> queryAllUsers() {
        return mapper.queryAllUsers();
    }
}
