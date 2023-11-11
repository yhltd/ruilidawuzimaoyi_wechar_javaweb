package com.myboot.service;

import com.myboot.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    //查询
    public List<User> gatAll();
    //添加
    public boolean insertUser(User user);
}
