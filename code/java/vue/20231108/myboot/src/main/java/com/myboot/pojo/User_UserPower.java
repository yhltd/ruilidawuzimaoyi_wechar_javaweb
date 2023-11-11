package com.myboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hui
 * @date 2023/11/6 10:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User_UserPower {
    private User user;
    private UserPower userPower;
    public String toString() {
        return user.getId() + " " +userPower.toString();
    }
}
