package com.myboot.service;

import com.myboot.pojo.XiaoShouBaoJiaItem;
import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.pojo.XiaoShouDingDanItem;

import java.util.List;

public interface XiaoShouDingDanItemService {

    //查询全部
    public List<XiaoShouDingDanItem> getAll();

    //根据报价id查询
    public List<XiaoShouDingDanItem> selectByXiaoShouId(Integer id);

    //增加
    public List<XiaoShouDingDanItem> xiaoShouItemAdd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer xiaoshou_id);

    //修改
    public List<XiaoShouDingDanItem> xiaoShouItemUpd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer xiaoshou_id,Integer id);

    //根据报价id删除
    public boolean deleteByXiaoShouId(Integer id);

}