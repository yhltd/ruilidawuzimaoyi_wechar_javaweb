package com.myboot.service;

import com.myboot.pojo.CaiGouRuKu;
import com.myboot.pojo.CaiGouRuKuItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaiGouRuKuItemService {

    //查询全部
    public List<CaiGouRuKuItem> getAll();

    //增加
    public List<CaiGouRuKuItem> caiGouRuKuItemAdd(String shangpin_bianma,String name,String guige,String caizhi,String jishu_biaozhun,String zhibao_dengji,String danwei,String shuliang,String lishi_zuidi,String caigou_danjia,String jiashui_xiaoji,String jiaohuo_riqi,String beizhu,Integer ruku_id);

    //修改
    public List<CaiGouRuKuItem> caiGouRuKuItemUpd(String shangpin_bianma,String name,String guige,String caizhi,String jishu_biaozhun,String zhibao_dengji,String danwei,String shuliang,String lishi_zuidi,String caigou_danjia,String jiashui_xiaoji,String jiaohuo_riqi,String beizhu,Integer ruku_id,Integer id);

    //根据入库id删除
    public boolean deleteByRuKuItemId(Integer id);

    //根据入库id查询
    public List<CaiGouRuKuItem> selectByRuKuItemId(Integer id);

}