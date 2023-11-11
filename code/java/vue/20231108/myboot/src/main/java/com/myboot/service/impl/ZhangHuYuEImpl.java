package com.myboot.service.impl;

import com.myboot.mapper.KuCunMapper;
import com.myboot.mapper.ZhangHuYuEMapper;
import com.myboot.pojo.KuCun;
import com.myboot.pojo.ZhangHuYuE;
import com.myboot.service.KuCunService;
import com.myboot.service.ZhangHuYuEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZhangHuYuEImpl implements ZhangHuYuEService {

    @Autowired
    private ZhangHuYuEMapper mapper;

    @Override
    public List<ZhangHuYuE> queryList(String riqi) {
        List<ZhangHuYuE> zhangHuYuE = mapper.queryList(riqi);
        return zhangHuYuE;
    }


}
