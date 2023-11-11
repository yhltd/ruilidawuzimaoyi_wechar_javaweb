package com.myboot.vo;

import lombok.Data;
import lombok.NonNull;

/**
 * 与前端互动User实体类
 * @author hui
 * @date 2023/11/6 10:08
 */
@Data
public class UserVO {
    /**
     * 用户账号
     */
    @NonNull
    private String username;
    /**
     * 用户密码
     */
    @NonNull
    private String password;
}
