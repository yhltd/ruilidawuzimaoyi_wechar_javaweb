package com.myboot.service.impl;

import com.myboot.mapper.CustomerItemMapper;
import com.myboot.mapper.CustomerMapper;
import com.myboot.mapper.GongYingShangItemMapper;
import com.myboot.mapper.GongYingShangMapper;
import com.myboot.pojo.Customer;
import com.myboot.pojo.GongYingShang;
import com.myboot.service.CustomerService;
import com.myboot.service.GongYingShangItemService;
import com.myboot.service.GongYingShangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GongYingShangImpl implements GongYingShangService {

    @Autowired
    private GongYingShangMapper mapper;

    @Autowired
    private GongYingShangItemMapper itemMapper;

    @Override
    public List<GongYingShang> getAll() {
        List<GongYingShang> gongYingShang = mapper.getAll();
        return gongYingShang;
    }

    @Override
    public List<GongYingShang> queryList(String bianhao,String name){
        List<GongYingShang> gongYingShang = mapper.queryList(bianhao,name);
        return gongYingShang;
    }

    @Override
    public int gongyingshangAdd(GongYingShang gongYingShang){
        int dingDan = mapper.gongyingshangAdd(gongYingShang);
        return dingDan;
    }

    @Override
    public int gongyingshangUpd(GongYingShang gongYingShang){
        int dingDan = mapper.gongyingshangupd(gongYingShang);
        return dingDan;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(List<Integer> list){
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteById(id);
            itemMapper.deleteById(id);
        }
        return count;
    }

    @Override
    public List<GongYingShang> selectById(Integer id){
        return mapper.selectById(id);
    }

}
