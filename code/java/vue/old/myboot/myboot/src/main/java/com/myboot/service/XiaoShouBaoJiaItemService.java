package com.myboot.service;

import com.myboot.pojo.CaiGouDingDanItem;
import com.myboot.pojo.XiaoShouBaoJiaItem;

import java.util.List;

public interface XiaoShouBaoJiaItemService {

    //查询全部
    public List<XiaoShouBaoJiaItem> getAll();

    //根据报价id查询
    public List<XiaoShouBaoJiaItem> selectByBaoJiaId(Integer id);

    //增加
    public List<XiaoShouBaoJiaItem> baoJiaItemAdd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer baojia_id);

    //修改
    public List<XiaoShouBaoJiaItem> baoJiaItemUpd(String shangpin_bianhao,String shangpin_mingcheng,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String baojia_danjia, String jiashui_xiaoji, String jianyi_baojia, String xuyong_riqi, String baojia_fudong, String beizhu, Integer baojia_id,Integer id);

    //根据报价id删除
    public boolean deleteByBaoJiaId(Integer id);

}