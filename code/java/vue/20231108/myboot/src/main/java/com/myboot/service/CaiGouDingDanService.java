package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanAdd;

import java.util.List;

public interface CaiGouDingDanService {

    //查询全部
    public List<CaiGouDingDan> getAll();

    //查询需要自己审核
    public List<CaiGouDingDan> getAllByShenHe(String shenhe);

    //条件查询
    public List<CaiGouDingDan> queryList(String start_date,String stop_date,String gongyingshang, String shenhe_zhuangtai);

    //增加
    public int caiGouDingDanAdd(CaiGouDingDan caiGouDingDan);

    //修改
    public int caiGouDingDanUpd(CaiGouDingDan caiGouDingDan);

    //根据采购id删除
    public int deleteByCaiGouId(Integer id);

    //根据采购id查询
    public List<CaiGouDingDan> selectByCaiGouId(Integer id);

    int delUsers(List<Integer> list);

}