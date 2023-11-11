package com.myboot.service;

import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouBaoJiaAdd;

import java.util.List;

public interface XiaoShouBaoJiaService {

    //查询全部
    public List<XiaoShouBaoJia> getAll();

    //查询需要自己审核
    public List<XiaoShouBaoJia> getAllByShenHe(String shenhe);

    //条件查询
    public List<XiaoShouBaoJia> queryList(String start_date,String stop_date,String kehu, String shenhe_zhuangtai);

    //增加
    public int baoJiaAdd(XiaoShouBaoJia xiaoShouBaoJia);

    //修改
    public int baoJiaUpd(XiaoShouBaoJia xiaoShouBaoJia);

    //根据采购id删除
    public int deleteBaoJiaById(Integer id);

    //根据采购id查询
    public List<XiaoShouBaoJia> selectBaoJiaById(Integer id);

    int delUsers(List<Integer> list);

}