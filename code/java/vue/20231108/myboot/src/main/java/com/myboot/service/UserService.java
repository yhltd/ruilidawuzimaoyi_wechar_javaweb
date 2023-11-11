package com.myboot.service;

import com.myboot.pojo.User;
import com.myboot.pojo.User_UserPower;
import com.myboot.vo.UserVO;

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

    List<User> queryShenpis();

    /**
     * 事务处理 捕获范围 Exception
     *
     * 会记录删除的总条数 如果未被删除且未报错不会对事务进行回滚
     * @param list
     * @return 成功删除的条数
     */
    int delUsers(List<Integer> list);

    /**
     * 模糊查询
     *
     * 模糊范围 name power shenpi userName
     * @param keyword
     * @return
     */
    List<User> fuzzyQuery(String keyword);
}
