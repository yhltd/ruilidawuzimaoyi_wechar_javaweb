package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouChuKuItemMapper;
import com.myboot.mapper.XiaoShouChuKuMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouChuKu;
import com.myboot.pojo.XiaoShouChuKuXiangQing;
import com.myboot.pojo.XiaoShouDingDanXiangQing;
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

    @Override
    public List<XiaoShouChuKuXiangQing> xiangQingShangPin(Integer id) {
        return mapper.xiangQingShangPin(id);
    }

    @Override
    public List<XiaoShouChuKuXiangQing> xiangQingShouKuan(String bianhao) {
        return mapper.xiangQingShouKuan(bianhao);
    }

    @Override
    public List<XiaoShouChuKuXiangQing> xiangQingKaiPiao(String bianhao) {
        return mapper.xiangQingKaiPiao(bianhao);
    }

    @Override
    public List<XiaoShouChuKuXiangQing> xiangQingDingJin(String bianhao) {
        return mapper.xiangQingDingJin(bianhao);
    }

    @Override
    public List<XiaoShouChuKuXiangQing> xiangQingDingJinYiYong(String bianhao) {
        return mapper.xiangQingDingJinYiYong(bianhao);
    }

    @Override
    public int chuKuDingJinUpd(Integer id, String dingjin) {
        return mapper.chuKuDingJinUpd(id,dingjin);
    }

    public List<XiaoShouChuKu> selectWeiFu(){
        return mapper.selectWeiFu();
    }

    @Override
    public List<XiaoShouChuKuXiangQing> xiangQingXuKaiPiao(String bianhao) {
        return mapper.xiangQingXuKaiPiao(bianhao);
    }

}
