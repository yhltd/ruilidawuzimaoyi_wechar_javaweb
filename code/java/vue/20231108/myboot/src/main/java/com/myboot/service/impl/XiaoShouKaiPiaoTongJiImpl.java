package com.myboot.service.impl;

import com.myboot.mapper.JiZhangMapper;
import com.myboot.mapper.XiaoShouKaiPiaoTongJiMapper;
import com.myboot.pojo.JiZhang;
import com.myboot.pojo.XiaoShouKaiPiaoTongJi;
import com.myboot.service.JiZhangService;
import com.myboot.service.XiaoShouKaiPiaoTongJiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouKaiPiaoTongJiImpl implements XiaoShouKaiPiaoTongJiService {

    @Autowired
    private XiaoShouKaiPiaoTongJiMapper mapper;

    @Override
    public List<XiaoShouKaiPiaoTongJi> getTongJiByDanWei(String start_date, String stop_date) {
        List<XiaoShouKaiPiaoTongJi> jiZhang = mapper.getTongJiByDanWei(start_date,stop_date);
        return jiZhang;
    }

    @Override
    public List<XiaoShouKaiPiaoTongJi> getTongJiByKeHu(String start_date, String stop_date) {
        List<XiaoShouKaiPiaoTongJi> jiZhang = mapper.getTongJiByKeHu(start_date,stop_date);
        return jiZhang;
    }


}
