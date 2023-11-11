package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouChuKuMapper;
import com.myboot.pojo.XiaoShouChuKu;
import com.myboot.service.XiaoShouChuKuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouChuKuImpl implements XiaoShouChuKuService {

    @Autowired
    private XiaoShouChuKuMapper mapper;

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
    public List<XiaoShouChuKu> chuKuAdd(String bianhao,String riqi,String kehu, String dianpu, String cangku,String beizhu,String xiaoshou_id){
        List<XiaoShouChuKu> xiaoShouChuKu = mapper.chuKuAdd(bianhao,riqi,kehu, dianpu, cangku,beizhu,xiaoshou_id);
        return xiaoShouChuKu;
    }

    @Override
    public List<XiaoShouChuKu> chuKuUpd(String bianhao,String riqi,String kehu, String dianpu, String cangku,String beizhu,String xiaoshou_id,Integer id){
        List<XiaoShouChuKu> xiaoShouChuKu = mapper.chuKuUpd(bianhao,riqi,kehu, dianpu, cangku,beizhu,xiaoshou_id,id);
        return xiaoShouChuKu;
    }

    @Override
    public boolean deleteByChuKuId(Integer id){
        return mapper.deleteByChuKuId(id);
    }

    @Override
    public List<XiaoShouChuKu> selectByChuKuId(Integer id) {
        return mapper.selectByChuKuId(id);
    }


}
