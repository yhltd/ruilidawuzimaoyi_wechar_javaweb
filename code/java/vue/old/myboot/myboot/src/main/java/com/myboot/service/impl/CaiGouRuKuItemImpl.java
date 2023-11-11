package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CaiGouRuKuItemMapper;
import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.CaiGouRuKuItem;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouRuKuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiGouRuKuItemImpl implements CaiGouRuKuItemService {

    @Autowired
    private CaiGouRuKuItemMapper mapper;

    @Override
    public List<CaiGouRuKuItem> getAll() {
        List<CaiGouRuKuItem> caiGouRuKuItem = mapper.getAll();
        return caiGouRuKuItem;
    }

    @Override
    public List<CaiGouRuKuItem> caiGouRuKuItemAdd(String shangpin_bianma,String name,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String lishi_zuidi, String caigou_danjia, String jiashui_xiaoji, String jiaohuo_riqi, String beizhu, Integer caigou_id){
        List<CaiGouRuKuItem> caiGouRuKuItem = mapper.caiGouRuKuAdd(shangpin_bianma,name,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,lishi_zuidi,caigou_danjia,jiashui_xiaoji,jiaohuo_riqi,beizhu,caigou_id);
        return caiGouRuKuItem;
    }

    @Override
    public List<CaiGouRuKuItem> caiGouRuKuItemUpd(String shangpin_bianma,String name,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String lishi_zuidi, String caigou_danjia, String jiashui_xiaoji, String jiaohuo_riqi, String beizhu, Integer caigou_id,Integer id){
        List<CaiGouRuKuItem> caiGouRuKuItem = mapper.caiGouRuKuUpd(shangpin_bianma,name,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,lishi_zuidi,caigou_danjia,jiashui_xiaoji,jiaohuo_riqi,beizhu,caigou_id,id);
        return caiGouRuKuItem;
    }

    @Override
    public boolean deleteByRuKuItemId(Integer id){
        return mapper.deleteByRuKuItemId(id);
    }

    @Override
    public List<CaiGouRuKuItem> selectByRuKuItemId(Integer id){
        return mapper.selectByRuKuItemId(id);
    }



}
