package com.myboot.service.impl;

import com.myboot.mapper.KuCunMapper;
import com.myboot.mapper.ShouZhiTongJiMapper;
import com.myboot.pojo.KuCun;
import com.myboot.pojo.ShouZhiTongJi;
import com.myboot.service.KuCunService;
import com.myboot.service.ShouZhiTongJiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShouZhiTongJiImpl implements ShouZhiTongJiService {

    @Autowired
    private ShouZhiTongJiMapper mapper;

    @Override
    public List<ShouZhiTongJi> getShouRu(String type, String riqi) {
        List<ShouZhiTongJi> shouZhiTongJi = null;
        if(type.equals("按日")){
            shouZhiTongJi = mapper.getShouRuByDay(type,riqi);
        }else if(type.equals("按月")){
            shouZhiTongJi = mapper.getShouRuByMonth(type,riqi);
        }
        return shouZhiTongJi;
    }

    @Override
    public List<ShouZhiTongJi> getZhiChu(String type, String riqi) {
        List<ShouZhiTongJi> shouZhiTongJi = null;
        if(type.equals("按日")){
            shouZhiTongJi = mapper.getZhiChuByDay(type,riqi);
        }else if(type.equals("按月")){
            shouZhiTongJi = mapper.getZhiChuByMonth(type,riqi);
        }
        return shouZhiTongJi;
    }

    @Override
    public List<ShouZhiTongJi> getShouZhi(String riqi) {
        List<ShouZhiTongJi> shouZhiTongJi = mapper.getShouZhiByMonth(riqi);
        return shouZhiTongJi;
    }

}
