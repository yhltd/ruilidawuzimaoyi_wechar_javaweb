package com.myboot.service.impl;

import com.myboot.mapper.CaiGouRuKuItemMapper;
import com.myboot.mapper.XiaoShouChuKuItemMapper;
import com.myboot.pojo.CaiGouRuKuItem;
import com.myboot.pojo.XiaoShouChuKuItem;
import com.myboot.service.CaiGouRuKuItemService;
import com.myboot.service.XiaoShouChuKuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouChuKuItemImpl implements XiaoShouChuKuItemService {

    @Autowired
    private XiaoShouChuKuItemMapper mapper;

    @Override
    public List<XiaoShouChuKuItem> getAll() {
        List<XiaoShouChuKuItem> xiaoShouChuKuItem = mapper.getAll();
        return xiaoShouChuKuItem;
    }

    @Override
    public List<XiaoShouChuKuItem> chuKuItemAdd(String shangpin_bianma,String name,String guige,String caizhi,String jishu_biaozhun,String zhibao_dengji,String danwei,String shuliang,String xiaoshou_danjia,String jiashui_xiaoji,String beizhu,Integer chuku_id){
        List<XiaoShouChuKuItem> xiaoShouChuKuItem = mapper.chuKuItemAdd(shangpin_bianma,name,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,xiaoshou_danjia,jiashui_xiaoji,beizhu,chuku_id);
        return xiaoShouChuKuItem;
    }

    @Override
    public List<XiaoShouChuKuItem> chuKuItemUpd(String shangpin_bianma,String name,String guige,String caizhi,String jishu_biaozhun,String zhibao_dengji,String danwei,String shuliang,String xiaoshou_danjia,String jiashui_xiaoji,String beizhu,Integer chuku_id,Integer id){
        List<XiaoShouChuKuItem> xiaoShouChuKuItem = mapper.chuKuItemUpd(shangpin_bianma,name,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,xiaoshou_danjia,jiashui_xiaoji,beizhu,chuku_id,id);
        return xiaoShouChuKuItem;
    }

    @Override
    public boolean deleteByChuKuItemId(Integer id){
        return mapper.deleteByChuKuItemId(id);
    }

    @Override
    public List<XiaoShouChuKuItem> selectByChuKuItemId(Integer id){
        return mapper.selectByChuKuItemId(id);
    }



}
