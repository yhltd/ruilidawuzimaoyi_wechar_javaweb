package com.myboot.service.impl;

import com.myboot.mapper.JiZhangMapper;
import com.myboot.mapper.QiChuGongYingShangMapper;
import com.myboot.mapper.QiChuKeHuMapper;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.QiChuGongYingShang;
import com.myboot.pojo.QiChuKeHu;
import com.myboot.service.JiZhangService;
import com.myboot.service.QiChuKeHuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QiChuKeHuImpl implements QiChuKeHuService {

    @Autowired
    private QiChuKeHuMapper mapper;

    @Override
    public List<QiChuKeHu> getAll() {
        List<QiChuKeHu> qiChuKeHu = mapper.getAll();
        return qiChuKeHu;
    }

    @Override
    public int add(QiChuKeHu qiChuKeHu) {
        Integer item = mapper.add(qiChuKeHu);
        return item;
    }

    @Override
    public int upd(QiChuKeHu qiChuKeHu) {
        Integer item = mapper.upd(qiChuKeHu);
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
