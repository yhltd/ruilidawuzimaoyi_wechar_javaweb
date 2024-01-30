package com.myboot.service.impl;

import com.myboot.mapper.JiZhangMapper;
import com.myboot.mapper.QiChuGongYingShangMapper;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.QiChuGongYingShang;
import com.myboot.service.JiZhangService;
import com.myboot.service.QiChuGongYingShangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QiChuGongYingShangImpl implements QiChuGongYingShangService {

    @Autowired
    private QiChuGongYingShangMapper mapper;

    @Override
    public List<QiChuGongYingShang> getAll() {
        List<QiChuGongYingShang> qiChuGongYingShang = mapper.getAll();
        return qiChuGongYingShang;
    }

    @Override
    public int add(QiChuGongYingShang qiChuGongYingShang) {
        Integer item = mapper.add(qiChuGongYingShang);
        return item;
    }

    @Override
    public int upd(QiChuGongYingShang qiChuGongYingShang) {
        Integer item = mapper.upd(qiChuGongYingShang);
        return item;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int del(List<Integer> list){
        int count = 0;
        for(Integer id : list) {
            count += mapper.del(id);
        }
        return count;
    }


}
