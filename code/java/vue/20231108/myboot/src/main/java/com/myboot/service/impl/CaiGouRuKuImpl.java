package com.myboot.service.impl;

import com.myboot.mapper.CaiGouRuKuItemMapper;
import com.myboot.mapper.CaiGouRuKuMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouRuKu;
import com.myboot.pojo.CaiGouRuKuAdd;
import com.myboot.service.CaiGouRuKuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaiGouRuKuImpl implements CaiGouRuKuService {

    @Autowired
    private CaiGouRuKuMapper mapper;
    @Autowired
    private CaiGouRuKuItemMapper itemMapper;

    @Override
    public List<CaiGouRuKu> getAll() {
        List<CaiGouRuKu> caiGouDingDan = mapper.getAll();
        return caiGouDingDan;
    }

    public List<CaiGouRuKu> queryList(String start_date,String stop_date,String gongyingshang){
        List<CaiGouRuKu> caiGouDingDan = mapper.queryList(start_date,stop_date,gongyingshang);
        return caiGouDingDan;
    }

    public int caiGouRuKuAdd(CaiGouRuKu caiGouRuKu){
        Integer ruKuAdd = mapper.caiGouRuKuAdd(caiGouRuKu);
        return ruKuAdd;
    }

    public int caiGouRuKuUpd(CaiGouRuKu caiGouRuKu){
        Integer ruKuUpd = mapper.caiGouRuKuUpd(caiGouRuKu);
        return ruKuUpd;
    }

    public int deleteByRuKuId(Integer id){
        return mapper.deleteByRuKuId(id);
    }

    public List<CaiGouRuKu> selectByRuKuId(Integer id){
        return mapper.selectByRuKuId(id);
    }

    public List<CaiGouRuKu> selectMaxDanHao(){
        return mapper.selectMaxDanHao();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteByRuKuId(id);
            itemMapper.deleteByRuKuItemId(id);
        }
        return count;
    }

    public List<CaiGouRuKu> selectWeiFu(){
        return mapper.selectWeiFu();
    }

}
