package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.XiaoShouBaoJiaItemMapper;
import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.XiaoShouBaoJiaItem;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.XiaoShouBaoJiaItemService;
import com.myboot.service.XiaoShouBaoJiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouBaoJiaItemImpl implements XiaoShouBaoJiaItemService {

    @Autowired
    private XiaoShouBaoJiaItemMapper mapper;

    @Override
    public List<XiaoShouBaoJiaItem> getAll() {
        List<XiaoShouBaoJiaItem> xiaoShouBaoJiaItem = mapper.getAll();
        return xiaoShouBaoJiaItem;
    }

    @Override
    public List<XiaoShouBaoJiaItem> baoJiaItemAdd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer baojia_id){
        List<XiaoShouBaoJiaItem> xiaoShouBaoJiaItem = mapper.baoJiaItemAdd(shangpin_bianhao,shangpin_mingcheng,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,baojia_danjia,jiashui_xiaoji,jianyi_baojia,xuyong_riqi,baojia_fudong,beizhu,baojia_id);
        return xiaoShouBaoJiaItem;
    }

    @Override
    public List<XiaoShouBaoJiaItem> baoJiaItemUpd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer baojia_id,Integer id){
        List<XiaoShouBaoJiaItem> xiaoShouBaoJiaItem = mapper.baoJiaItemUpd(shangpin_bianhao,shangpin_mingcheng,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,baojia_danjia,jiashui_xiaoji,jianyi_baojia,xuyong_riqi,baojia_fudong,beizhu,baojia_id,id);
        return xiaoShouBaoJiaItem;
    }

    @Override
    public boolean deleteByBaoJiaId(Integer id){
        return mapper.deleteByBaoJiaId(id);
    }

    @Override
    public List<XiaoShouBaoJiaItem> selectByBaoJiaId(Integer id){
        return mapper.selectByBaoJiaId(id);
    }



}
