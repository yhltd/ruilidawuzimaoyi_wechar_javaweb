package com.myboot.service.impl;

import com.myboot.mapper.UserMapper;
import com.myboot.pojo.User;
import com.myboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

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
}
