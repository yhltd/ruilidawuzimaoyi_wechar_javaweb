package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanItem;

import java.util.List;

public interface CaiGouDingDanItemService {

    //查询全部
    public List<CaiGouDingDanItem> getAll();

    //根据采购id查询
    public List<CaiGouDingDanItem> selectByCaiGouId(Integer id);

    //增加
    public List<CaiGouDingDanItem> caiGouDingDanItemAdd(String shangpin_bianma,String name,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String lishi_zuidi, String caigou_danjia, String jiashui_xiaoji, String jiaohuo_riqi, String beizhu, Integer caigou_id);

    //修改
    public List<CaiGouDingDanItem> caiGouDingDanItemUpd(String shangpin_bianma,String name,String guige, String caizhi, String jishu_biaozhun,String zhibao_dengji,String danwei, String shuliang, String lishi_zuidi, String caigou_danjia, String jiashui_xiaoji, String jiaohuo_riqi, String beizhu, Integer caigou_id,Integer id);

    //根据采购id删除
    public boolean deleteByCaiGouId(Integer id);



}