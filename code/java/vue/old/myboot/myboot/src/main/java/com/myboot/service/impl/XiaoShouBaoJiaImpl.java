package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.mapper.XiaoShouBaoJiaMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.XiaoShouBaoJiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouBaoJiaImpl implements XiaoShouBaoJiaService {

    @Autowired
    private XiaoShouBaoJiaMapper mapper;

    @Override
    public List<XiaoShouBaoJia> getAll() {
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.getAll();
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> getAllByShenHe(String shenhe) {
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.getAllByShenHe(shenhe);
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> queryList(String start_date,String stop_date,String gongyingshang, String shenhe_zhuangtai){
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.queryList(start_date,stop_date,gongyingshang,shenhe_zhuangtai);
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> baoJiaAdd(String bianhao,String riqi,String kehu, String yewuyuan, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String jiage_dengji, String shenhe_zhuangtai){
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.baoJiaAdd(bianhao,riqi,kehu, yewuyuan, dianpu,xiaoxiang_shuilv,beizhu, shenhe, jiage_dengji, shenhe_zhuangtai);
        return xiaoShouBaoJia;
    }

    @Override
    public List<XiaoShouBaoJia> baoJiaUpd(String bianhao,String riqi,String kehu, String yewuyuan, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String jiage_dengji, String shenhe_zhuangtai,Integer id){
        List<XiaoShouBaoJia> xiaoShouBaoJia = mapper.baoJiaUpd(bianhao,riqi,kehu, yewuyuan, dianpu,xiaoxiang_shuilv,beizhu, shenhe, jiage_dengji, shenhe_zhuangtai,id);
        return xiaoShouBaoJia;
    }

    @Override
    public boolean deleteBaoJiaById(Integer id){
        return mapper.deleteBaoJiaById(id);
    }

    @Override
    public List<XiaoShouBaoJia> selectBaoJiaById(Integer id){
        return mapper.selectBaoJiaById(id);
    }


}
