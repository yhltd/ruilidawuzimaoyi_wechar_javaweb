package com.myboot.service.impl;

import com.myboot.mapper.PeizhiMapper;
import com.myboot.pojo.Peizhi;
import com.myboot.pojo.PeizhiTypeEnum;
import com.myboot.service.PeizhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/8 11:21
 */
@Service
public class PeizhiServiceImpl implements PeizhiService {

    @Autowired
    private PeizhiMapper peizhiMapper;

    @Override
    public List<Peizhi> queryAllByType(String type) {
        return peizhiMapper.queryAllByType(type);
    }

    @Override
    public int peiZhiAdd(Peizhi peizhi) {
        return peizhiMapper.peiZhiAdd(peizhi);
    }

    @Override
    public int peiZhiUpd(Peizhi peizhi) {
        return peizhiMapper.peiZhiUpd(peizhi);
    }

    @Override
    public int deleteByPeiZhiId(Integer id) {
        return peizhiMapper.deleteByPeiZhiId(id);
    }

    @Override
    public List<Peizhi> selectByPeiZhiId(Integer id) {
        return peizhiMapper.selectByPeiZhiId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += peizhiMapper.deleteByPeiZhiId(id);
        }
        return count;
    }

    @Override
    public List<Peizhi> addChaChong(String name, String type) {
        return peizhiMapper.addChaChong(name,type);
    }

    @Override
    public List<Peizhi> updChaChong(String name, String type, Integer id) {
        return peizhiMapper.updChaChong(name,type,id);
    }


}
