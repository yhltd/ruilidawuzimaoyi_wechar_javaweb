package com.myboot.service.impl;

import com.myboot.mapper.JiZhangMapper;
import com.myboot.mapper.QiChuKeHuMapper;
import com.myboot.mapper.QiChuProductMapper;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.QiChuKeHu;
import com.myboot.pojo.QiChuProduct;
import com.myboot.service.JiZhangService;
import com.myboot.service.QiChuProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QiChuProductImpl implements QiChuProductService {

    @Autowired
    private QiChuProductMapper mapper;

    @Override
    public List<QiChuProduct> getAll() {
        List<QiChuProduct> qiChuProduct = mapper.getAll();
        return qiChuProduct;
    }

    @Override
    public int add(QiChuProduct qiChuProduct) {
        Integer item = mapper.add(qiChuProduct);
        return item;
    }

    @Override
    public int upd(QiChuProduct qiChuProduct) {
        Integer item = mapper.upd(qiChuProduct);
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
