package com.myboot.service;

import com.myboot.vo.UserVO;

/**
 * V零
 * 用于用户登录、登出
 * @author hui
 * @date 2023/11/6 10:11
 */
public interface V0Service {
    /**
     * 登录接口
     * @param vo
     */
    public void login(UserVO vo);
    public void logout();
}
