package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouDingDanItemMapper;
import com.myboot.mapper.XiaoShouDingDanMapper;
import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.pojo.XiaoShouDingDanAdd;
import com.myboot.service.XiaoShouDingDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XiaoShouDingDanImpl implements XiaoShouDingDanService {

    @Autowired
    private XiaoShouDingDanMapper mapper;

    @Autowired
    private XiaoShouDingDanItemMapper itemMapper;

    @Override
    public List<XiaoShouDingDan> getAll() {
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.getAll();
        return xiaoShouDingDan;
    }

    @Override
    public List<XiaoShouDingDan> getAllByShenHe(String shenhe) {
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.getAllByShenHe(shenhe);
        return xiaoShouDingDan;
    }

    @Override
    public List<XiaoShouDingDan> queryList(String start_date,String stop_date,String kehu, String shenhe_zhuangtai){
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.queryList(start_date,stop_date,kehu,shenhe_zhuangtai);
        return xiaoShouDingDan;
    }

    @Override
    public int xiaoShouAdd(XiaoShouDingDan xiaoShouDingDan){
        Integer dingDan = mapper.xiaoShouAdd(xiaoShouDingDan);
        return dingDan;
    }

    @Override
    public int xiaoShouUpd(XiaoShouDingDan xiaoShouDingDan){
        Integer dingDan = mapper.xiaoShouUpd(xiaoShouDingDan);
        return dingDan;
    }

    @Override
    public int deleteXiaoShouById(Integer id){
        return mapper.deleteXiaoShouById(id);
    }

    @Override
    public List<XiaoShouDingDan> selectXiaoShouById(Integer id){
        return mapper.selectXiaoShouById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteXiaoShouById(id);
            itemMapper.deleteByXiaoShouId(id);
        }
        return count;
    }

}
