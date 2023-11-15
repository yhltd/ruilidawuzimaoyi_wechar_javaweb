package com.myboot.service;

import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouBaoJiaAdd;
import com.myboot.pojo.XiaoShouChuKu;

import java.util.List;

public interface XiaoShouBaoJiaService {

    //查询全部
    public List<XiaoShouBaoJia> getAll();

    public List<XiaoShouBaoJia> getAllByName(String yewuyuan);

    //查询需要自己审核
    public List<XiaoShouBaoJia> getAllByShenHe(String shenhe);

    //条件查询
    public List<XiaoShouBaoJia> queryList(String start_date,String stop_date,String kehu, String shenhe_zhuangtai);


    public List<XiaoShouBaoJia> queryListByName(String yewuyuan,String start_date,String stop_date,String kehu, String shenhe_zhuangtai);

    //增加
    public int baoJiaAdd(XiaoShouBaoJia xiaoShouBaoJia);

    //修改
    public int baoJiaUpd(XiaoShouBaoJia xiaoShouBaoJia);

    //根据采购id删除
    public int deleteBaoJiaById(Integer id);

    //根据采购id查询
    public List<XiaoShouBaoJia> selectBaoJiaById(Integer id);

    int delUsers(List<Integer> list);

    //修改
    public int baoJiaShenHe(List<Integer> list,String type);

    public List<XiaoShouBaoJia> selectMaxDanHao();

    public List<XiaoShouBaoJia> shenheList(String name);

}