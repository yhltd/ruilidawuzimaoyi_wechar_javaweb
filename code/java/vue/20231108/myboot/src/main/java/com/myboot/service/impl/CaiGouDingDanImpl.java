package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanAdd;
import com.myboot.service.CaiGouDingDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaiGouDingDanImpl implements CaiGouDingDanService {

    @Autowired
    private CaiGouDingDanMapper mapper;

    @Autowired
    private CaiGouDingDanItemMapper itemMapper;

    @Override
    public List<CaiGouDingDan> getAll() {
        List<CaiGouDingDan> caiGouDingDan = mapper.getAll();
        return caiGouDingDan;
    }

    @Override
    public List<CaiGouDingDan> getAllByShenHe(String shenhe) {
        List<CaiGouDingDan> caiGouDingDan = mapper.getAllByShenHe(shenhe);
        return caiGouDingDan;
    }

    public List<CaiGouDingDan> queryList(String start_date,String stop_date,String gongyingshang, String shenhe_zhuangtai){
        List<CaiGouDingDan> caiGouDingDan = mapper.queryList(start_date,stop_date,gongyingshang,shenhe_zhuangtai);
        return caiGouDingDan;
    }

    public int caiGouDingDanAdd(CaiGouDingDan caiGouDingDan){
        int dingDan = mapper.caiGouDingDanAdd(caiGouDingDan);
        return dingDan;
    }

    public int caiGouDingDanUpd(CaiGouDingDan caiGouDingDan){
        int dingDan = mapper.caiGouDingDanUpd(caiGouDingDan);
        return dingDan;
    }

    public int deleteByCaiGouId(Integer id){
        return mapper.deleteByCaiGouId(id);
    }

    public List<CaiGouDingDan> selectByCaiGouId(Integer id){
        return mapper.selectByCaiGouId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteByCaiGouId(id);
            itemMapper.deleteByCaiGouId(id);
        }
        return count;
    }


}
