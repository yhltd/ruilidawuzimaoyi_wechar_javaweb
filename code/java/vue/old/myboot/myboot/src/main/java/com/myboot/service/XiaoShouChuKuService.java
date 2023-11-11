package com.myboot.service;

import com.myboot.pojo.XiaoShouChuKu;

import java.util.List;

public interface XiaoShouChuKuService {

    //查询全部
    public List<XiaoShouChuKu> getAll();

    //条件查询
    public List<XiaoShouChuKu> queryList(String start_date,String stop_date,String kehu);

    //增加
    public List<XiaoShouChuKu> chuKuAdd(String bianhao,String riqi,String kehu, String dianpu, String cangku,String beizhu,String xiaoshou_id);

    //修改
    public List<XiaoShouChuKu> chuKuUpd(String bianhao,String riqi,String kehu, String dianpu, String cangku,String beizhu,String xiaoshou_id,Integer id);

    //根据采购id删除
    public boolean deleteByChuKuId(Integer id);

    //根据采购id查询
    public List<XiaoShouChuKu> selectByChuKuId(Integer id);

}