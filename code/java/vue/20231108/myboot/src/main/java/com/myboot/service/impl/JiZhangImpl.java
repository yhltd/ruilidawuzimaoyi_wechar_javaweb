package com.myboot.service.impl;

import com.myboot.mapper.JiZhangMapper;
import com.myboot.mapper.KuCunMapper;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.KuCun;
import com.myboot.service.JiZhangService;
import com.myboot.service.KuCunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiZhangImpl implements JiZhangService {

    @Autowired
    private JiZhangMapper mapper;

    @Override
    public List<JiZhang> getJiZhang(String start_date, String stop_date, String dianpu, String jizhang_danwei, String jizhang_zhanghu) {
        List<JiZhang> jiZhang = mapper.getJiZhang(start_date,stop_date,dianpu,jizhang_danwei,jizhang_zhanghu);
        return jiZhang;
    }


}
