package com.myboot.mapper;

import com.myboot.pojo.User;
import com.myboot.pojo.User_Peizhi;
import com.myboot.pojo.User_UserPower;
import com.myboot.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper  {
    public List<User> getAll();

    public int insertUser(User user);

    /**
     * 登录
     * @param userVO
     * @return
     */
    User login(UserVO userVO);

    User getUserInfoById(@Param("id") Integer id);

    boolean updateById(User user);

    List<User> queryAllUsers();

    List<User> queryShenpis();

    int deleteUsrById(Integer id);

    List<User> fuzzyQuery(String keyword);

    boolean updCangKu(String typeId,Integer id);

    boolean updDianPu(String typeId,Integer id);

    boolean updHeSuanDanWei(String typeId,Integer id);

    boolean updShouKuanZhangHu(String typeId,Integer id);

    boolean updZhiBaoDengJi(String typeId,Integer id);
}
