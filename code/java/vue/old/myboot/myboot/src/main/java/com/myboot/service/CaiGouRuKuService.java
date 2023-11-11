package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouRuKu;

import java.util.List;

public interface CaiGouRuKuService {

    //查询全部
    public List<CaiGouRuKu> getAll();

    //条件查询
    public List<CaiGouRuKu> queryList(String start_date,String stop_date,String gongyingshang);

    //增加
    public List<CaiGouRuKu> caiGouRuKuAdd(String bianhao,String riqi,String gongyingshang, String dianpu, String cangku,String beizhu,String caigou_id);

    //修改
    public List<CaiGouRuKu> caiGouRuKuUpd(String bianhao,String riqi,String gongyingshang, String dianpu, String cangku,String beizhu,String caigou_id,Integer id);

    //根据采购id删除
    public boolean deleteByRuKuId(Integer id);

    //根据采购id查询
    public List<CaiGouRuKu> selectByRuKuId(Integer id);

}