package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiGouDingDanItemImpl implements CaiGouDingDanItemService {

    @Autowired
    private CaiGouDingDanItemMapper mapper;

    @Override
    public List<CaiGouDingDanItem> getAll() {
        List<CaiGouDingDanItem> caiGouDingDanItem = mapper.getAll();
        return caiGouDingDanItem;
    }

    @Override
    public List<CaiGouDingDanItem> caiGouDingDanItemAdd(String shangpin_bianma,String name,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String lishi_zuidi, String caigou_danjia, String jiashui_xiaoji, String jiaohuo_riqi, String beizhu, Integer caigou_id){
        List<CaiGouDingDanItem> caiGouDingDanItem = mapper.caiGouDingDanAdd(shangpin_bianma,name,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,lishi_zuidi,caigou_danjia,jiashui_xiaoji,jiaohuo_riqi,beizhu,caigou_id);
        return caiGouDingDanItem;
    }

    @Override
    public List<CaiGouDingDanItem> caiGouDingDanItemUpd(String shangpin_bianma,String name,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String lishi_zuidi, String caigou_danjia, String jiashui_xiaoji, String jiaohuo_riqi, String beizhu, Integer caigou_id,Integer id){
        List<CaiGouDingDanItem> caiGouDingDanItem = mapper.caiGouDingDanUpd(shangpin_bianma,name,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,lishi_zuidi,caigou_danjia,jiashui_xiaoji,jiaohuo_riqi,beizhu,caigou_id,id);
        return caiGouDingDanItem;
    }

    @Override
    public boolean deleteByCaiGouId(Integer id){
        return mapper.deleteByCaiGouId(id);
    }

    @Override
    public List<CaiGouDingDanItem> selectByCaiGouId(Integer id){
        return mapper.selectByCaiGouId(id);
    }



}
