package com.myboot.service.impl;

import com.myboot.mapper.AccountingUnitMapper;
import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.pojo.AccountingUnit;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.service.AccountingUnitService;
import com.myboot.service.CaiGouDingDanService;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiGouDingDanImpl implements CaiGouDingDanService {

    @Autowired
    private CaiGouDingDanMapper mapper;

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

    public List<CaiGouDingDan> caiGouDingDanAdd(String bianhao,String riqi,String gongyingshang, String dianpu, String jinxiangShuilv,String beizhu,String shenhe, String shenheZhuangtai){
        List<CaiGouDingDan> caiGouDingDan = mapper.caiGouDingDanAdd(bianhao,riqi,gongyingshang,dianpu,jinxiangShuilv,beizhu,shenhe,shenheZhuangtai);
        return caiGouDingDan;
    }

    public List<CaiGouDingDan> caiGouDingDanUpd(String bianhao,String riqi,String gongyingshang, String dianpu, String jinxiangShuilv,String beizhu,String shenhe, String shenheZhuangtai,Integer id){
        List<CaiGouDingDan> caiGouDingDan = mapper.caiGouDingDanUpd(bianhao,riqi,gongyingshang,dianpu,jinxiangShuilv,beizhu,shenhe,shenheZhuangtai,id);
        return caiGouDingDan;
    }

    public boolean deleteByCaiGouId(Integer id){
        return mapper.deleteByCaiGouId(id);
    }

    public List<CaiGouDingDan> selectByCaiGouId(Integer id){
        return mapper.selectByCaiGouId(id);
    }


}
