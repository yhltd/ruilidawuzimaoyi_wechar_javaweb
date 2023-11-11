package com.myboot.service.impl;

import com.myboot.mapper.XiaoShouBaoJiaItemMapper;
import com.myboot.mapper.XiaoShouDingDanItemMapper;
import com.myboot.mapper.XiaoShouDingDanMapper;
import com.myboot.pojo.XiaoShouBaoJiaItem;
import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.pojo.XiaoShouDingDanItem;
import com.myboot.service.XiaoShouBaoJiaItemService;
import com.myboot.service.XiaoShouDingDanItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XiaoShouDingDanItemImpl implements XiaoShouDingDanItemService {

    @Autowired
    private XiaoShouDingDanItemMapper mapper;

    @Override
    public List<XiaoShouDingDanItem> getAll() {
        List<XiaoShouDingDanItem> xiaoShouDingDanItem = mapper.getAll();
        return xiaoShouDingDanItem;
    }

    @Override
    public List<XiaoShouDingDanItem> xiaoShouItemAdd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer baojia_id){
        List<XiaoShouDingDanItem> xiaoShouDingDanItem = mapper.xiaoShouItemAdd(shangpin_bianhao,shangpin_mingcheng,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,baojia_danjia,jiashui_xiaoji,jianyi_baojia,xuyong_riqi,baojia_fudong,beizhu,baojia_id);
        return xiaoShouDingDanItem;
    }

    @Override
    public List<XiaoShouDingDanItem>xiaoShouItemUpd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer baojia_id,Integer id){
        List<XiaoShouDingDanItem> xiaoShouDingDanItem = mapper.xiaoShouItemUpd(shangpin_bianhao,shangpin_mingcheng,guige,caizhi,jishu_biaozhun,zhibao_dengji,danwei,shuliang,baojia_danjia,jiashui_xiaoji,jianyi_baojia,xuyong_riqi,baojia_fudong,beizhu,baojia_id,id);
        return xiaoShouDingDanItem;
    }

    @Override
    public boolean deleteByXiaoShouId(Integer id){
        return mapper.deleteByXiaoShouId(id);
    }

    @Override
    public List<XiaoShouDingDanItem> selectByXiaoShouId(Integer id){
        return mapper.selectByXiaoShouId(id);
    }


}
