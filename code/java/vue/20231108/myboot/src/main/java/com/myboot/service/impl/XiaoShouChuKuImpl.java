package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouChuKuItemMapper;
import com.myboot.mapper.XiaoShouChuKuMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouChuKu;
import com.myboot.service.XiaoShouChuKuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XiaoShouChuKuImpl implements XiaoShouChuKuService {

    @Autowired
    private XiaoShouChuKuMapper mapper;

    @Autowired
    private XiaoShouChuKuItemMapper itemMapper;

    @Override
    public List<XiaoShouChuKu> getAll() {
        List<XiaoShouChuKu> xiaoShouChuKu = mapper.getAll();
        return xiaoShouChuKu;
    }

    @Override
    public List<XiaoShouChuKu> queryList(String start_date,String stop_date,String kehu){
        List<XiaoShouChuKu> xiaoShouChuKu = mapper.queryList(start_date,stop_date,kehu);
        return xiaoShouChuKu;
    }

    @Override
    public int chuKuAdd(XiaoShouChuKu xiaoShouChuKu){
        Integer chuKu = mapper.chuKuAdd(xiaoShouChuKu);
        return chuKu;
    }

    @Override
    public int chuKuUpd(XiaoShouChuKu xiaoShouChuKu){
        Integer chuKu = mapper.chuKuUpd(xiaoShouChuKu);
        return chuKu;
    }

    @Override
    public int deleteByChuKuId(Integer id){
        return mapper.deleteByChuKuId(id);
    }

    @Override
    public List<XiaoShouChuKu> selectByChuKuId(Integer id) {
        return mapper.selectByChuKuId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteByChuKuId(id);
            itemMapper.deleteByChuKuItemId(id);
        }
        return count;
    }

    public List<XiaoShouChuKu> selectMaxDanHao(){
        return mapper.selectMaxDanHao();
    }

    public List<XiaoShouChuKu> selectWeiFu(){
        return mapper.selectWeiFu();
    }


}
