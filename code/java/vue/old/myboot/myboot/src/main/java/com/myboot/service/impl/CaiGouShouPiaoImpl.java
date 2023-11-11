package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.mapper.CaiGouShouPiaoMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.CaiGouShouPiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiGouShouPiaoImpl implements CaiGouShouPiaoService {

    @Autowired
    private CaiGouShouPiaoMapper mapper;

    @Override
    public List<CaiGouShouPiao> getAll() {
        List<CaiGouShouPiao> caiGouShouPiao = mapper.getAll();
        return caiGouShouPiao;
    }

    @Override
    public List<CaiGouShouPiao> getAllByShenHe(String xinxi_tuisong) {
        List<CaiGouShouPiao> caiGouShouPiao = mapper.getAllByShenHe(xinxi_tuisong);
        return caiGouShouPiao;
    }

    public List<CaiGouShouPiao> queryList(String start_date,String stop_date,String kaipiao_danwei,String shoupiao_danwei,String shoupiao_zhuangtai){
        List<CaiGouShouPiao> caiGouShouPiao = mapper.queryList(start_date,stop_date,kaipiao_danwei,shoupiao_danwei,shoupiao_zhuangtai);
        return caiGouShouPiao;
    }

    public List<CaiGouShouPiao> caiGouShouPiaoAdd(String caigou_bianhao,String shoupiao_danwei,String kaipiao_danwei,String kaipiao_riqi,String kaipiao_jine,String kaipiao_shuie,String jiashui_heji,String beizhu,String xinxi_tuisong,String shoupiao_zhuangtai){
        List<CaiGouShouPiao> caiGouShouPiao = mapper.caiGouShouPiaoAdd(caigou_bianhao,shoupiao_danwei,kaipiao_danwei,kaipiao_riqi,kaipiao_jine,kaipiao_shuie,jiashui_heji,beizhu,xinxi_tuisong,shoupiao_zhuangtai);
        return caiGouShouPiao;
    }

    public List<CaiGouShouPiao> caiGouShouPiaoUpd(String caigou_bianhao,String shoupiao_danwei,String kaipiao_danwei,String kaipiao_riqi,String kaipiao_jine,String kaipiao_shuie,String jiashui_heji,String beizhu,String xinxi_tuisong,String shoupiao_zhuangtai,Integer id){
        List<CaiGouShouPiao> caiGouShouPiao = mapper.caiGouShouPiaoUpd(caigou_bianhao,shoupiao_danwei,kaipiao_danwei,kaipiao_riqi,kaipiao_jine,kaipiao_shuie,jiashui_heji,beizhu,xinxi_tuisong,shoupiao_zhuangtai,id);
        return caiGouShouPiao;
    }

    public boolean deleteByShouPiaoId(Integer id){
        return mapper.deleteByShouPiaoId(id);
    }

    public List<CaiGouShouPiao> selectByShouPiaoId(Integer id){
        return mapper.selectByShouPiaoId(id);
    }


}
