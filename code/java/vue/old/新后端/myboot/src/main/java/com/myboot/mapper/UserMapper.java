package com.myboot.mapper;

import com.myboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper  {
    public List<User> getAll();

    public int insertUser(User user);
}
