package com.myboot.service;

import com.myboot.pojo.XiaoShouChuKuItem;

import java.util.List;

public interface XiaoShouChuKuItemService {

    //查询全部
    public List<XiaoShouChuKuItem> getAll();

    //增加
    public List<XiaoShouChuKuItem> chuKuItemAdd(String shangpin_bianma,String name,String guige,String caizhi,String jishu_biaozhun,String zhibao_dengji,String danwei,String shuliang,String xiaoshou_danjia,String jiashui_xiaoji,String beizhu,Integer chuku_id);

    //修改
    public List<XiaoShouChuKuItem> chuKuItemUpd(String shangpin_bianma,String name,String guige,String caizhi,String jishu_biaozhun,String zhibao_dengji,String danwei,String shuliang,String xiaoshou_danjia,String jiashui_xiaoji,String beizhu,Integer chuku_id,Integer id);

    //根据入库id删除
    public boolean deleteByChuKuItemId(Integer id);

    //根据入库id查询
    public List<XiaoShouChuKuItem> selectByChuKuItemId(Integer id);

}