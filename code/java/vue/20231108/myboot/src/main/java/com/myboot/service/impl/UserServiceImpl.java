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
import org.springframework.transaction.annotation.Transactional;

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
        return mapper.insertUser(user) == 1;
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

    @Override
    public List<User> queryShenpis() {
        return mapper.queryShenpis();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteUsrById(id);
        }
        return count;
    }

    @Override
    public List<User> fuzzyQuery(String keyword) {
        return mapper.fuzzyQuery(keyword);
    }

    @Override
    public boolean updateMoRen(String type,String typeId,Integer id) {
        if(type.equals("仓库")){
            return mapper.updCangKu(typeId,id);
        }else if(type.equals("店铺")){
            return mapper.updDianPu(typeId,id);
        }else if(type.equals("核算单位")){
            return mapper.updHeSuanDanWei(typeId,id);
        }
        return true;
    }
}
