package com.myboot.service;

import com.myboot.pojo.User;
import com.myboot.pojo.User_UserPower;
import com.myboot.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    //查询
    public List<User> gatAll();
    //添加
    public boolean insertUser(User user);

    /**
     * 用户登录接口
     * @param userVO
     * @return
     */
    User_UserPower login(UserVO userVO);

    /**
     * 通过id获取用户信息
     * @param id 用户id
     * @return
     */
    User getUserInfoById(Integer id);

    boolean updateById(User user);

    List<User> queryAllUsers();
}
