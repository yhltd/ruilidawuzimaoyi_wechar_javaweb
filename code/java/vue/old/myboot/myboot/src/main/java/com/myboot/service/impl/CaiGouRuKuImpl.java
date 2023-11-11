package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.mapper.CaiGouRuKuMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouRuKu;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.CaiGouRuKuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiGouRuKuImpl implements CaiGouRuKuService {

    @Autowired
    private CaiGouRuKuMapper mapper;

    @Override
    public List<CaiGouRuKu> getAll() {
        List<CaiGouRuKu> caiGouDingDan = mapper.getAll();
        return caiGouDingDan;
    }

    public List<CaiGouRuKu> queryList(String start_date,String stop_date,String gongyingshang){
        List<CaiGouRuKu> caiGouDingDan = mapper.queryList(start_date,stop_date,gongyingshang);
        return caiGouDingDan;
    }

    public List<CaiGouRuKu> caiGouRuKuAdd(String bianhao,String riqi,String gongyingshang, String dianpu, String cangku,String beizhu,String ruku_id){
        List<CaiGouRuKu> caiGouDingDan = mapper.caiGouRuKuAdd(bianhao,riqi,gongyingshang, dianpu, cangku,beizhu,ruku_id);
        return caiGouDingDan;
    }

    public List<CaiGouRuKu> caiGouRuKuUpd(String bianhao,String riqi,String gongyingshang, String dianpu, String cangku,String beizhu,String ruku_id,Integer id){
        List<CaiGouRuKu> caiGouDingDan = mapper.caiGouRuKuUpd(bianhao,riqi,gongyingshang, dianpu, cangku,beizhu,ruku_id,id);
        return caiGouDingDan;
    }

    public boolean deleteByRuKuId(Integer id){
        return mapper.deleteByRuKuId(id);
    }

    public List<CaiGouRuKu> selectByRuKuId(Integer id){
        return mapper.selectByRuKuId(id);
    }


}
