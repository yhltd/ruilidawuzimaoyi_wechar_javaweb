package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.mapper.JiZhangMingXiMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanXiangQing;
import com.myboot.pojo.JiZhangMingXi;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.JiZhangMingXiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JiZhangMingXiImpl implements JiZhangMingXiService {

    @Autowired
    private JiZhangMingXiMapper mapper;


    @Override
    public List<JiZhangMingXi> getAll() {
        List<JiZhangMingXi> jiZhangMingXi = mapper.getAll();
        return jiZhangMingXi;
    }


    public int updateById(JiZhangMingXi jiZhangMingXi){
        int dingDan = mapper.updateById(jiZhangMingXi);
        return dingDan;
    }


}
