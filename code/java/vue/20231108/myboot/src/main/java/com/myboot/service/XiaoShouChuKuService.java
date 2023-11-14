package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouChuKu;
import com.myboot.pojo.XiaoShouDingDan;

import java.util.List;

public interface XiaoShouChuKuService {

    //查询全部
    public List<XiaoShouChuKu> getAll();

    //条件查询
    public List<XiaoShouChuKu> queryList(String start_date,String stop_date,String kehu);

    //增加
    public int chuKuAdd(XiaoShouChuKu xiaoShouChuKu);

    //修改
    public int chuKuUpd(XiaoShouChuKu xiaoShouChuKu);

    //根据采购id删除
    public int deleteByChuKuId(Integer id);

    //根据采购id查询
    public List<XiaoShouChuKu> selectByChuKuId(Integer id);

    int delUsers(List<Integer> list);

    public List<XiaoShouChuKu> selectWeiFu();

    public List<XiaoShouChuKu> selectMaxDanHao();

}