package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouBaoJiaMapper;
import com.myboot.mapper.XiaoShouDingDanMapper;
import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.service.XiaoShouBaoJiaService;
import com.myboot.service.XiaoShouDingDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouDingDanImpl implements XiaoShouDingDanService {

    @Autowired
    private XiaoShouDingDanMapper mapper;

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
    public List<XiaoShouDingDan> xiaoShouAdd(String bianhao,String riqi,String kehu, String yewuyuan, String shoujianren, String shoujian_phone, String shoujian_dizhi, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String shenhe_zhuangtai, String jiashui_heji, String jiage_dengji){
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.xiaoShouAdd(bianhao,riqi,kehu, yewuyuan, shoujianren,shoujian_phone,shoujian_dizhi, dianpu, xiaoxiang_shuilv, beizhu,shenhe,shenhe_zhuangtai,jiashui_heji,jiage_dengji);
        return xiaoShouDingDan;
    }

    @Override
    public List<XiaoShouDingDan> xiaoShouUpd(String bianhao,String riqi,String kehu, String yewuyuan, String shoujianren, String shoujian_phone, String shoujian_dizhi, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String shenhe_zhuangtai, String jiashui_heji, String jiage_dengji,Integer id){
        List<XiaoShouDingDan> xiaoShouDingDan = mapper.xiaoShouUpd(bianhao,riqi,kehu, yewuyuan, shoujianren,shoujian_phone,shoujian_dizhi, dianpu, xiaoxiang_shuilv, beizhu,shenhe,shenhe_zhuangtai,jiashui_heji,jiage_dengji,id);
        return xiaoShouDingDan;
    }

    @Override
    public boolean deleteXiaoShouById(Integer id){
        return mapper.deleteXiaoShouById(id);
    }

    @Override
    public List<XiaoShouDingDan> selectXiaoShouById(Integer id){
        return mapper.selectXiaoShouById(id);
    }


}
