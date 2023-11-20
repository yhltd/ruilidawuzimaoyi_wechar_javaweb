package com.myboot.service;

import com.myboot.pojo.Customer;
import com.myboot.pojo.GongYingShang;
import com.myboot.pojo.XiaoShouBaoJia;

import java.util.List;

public interface GongYingShangService {

    //查询全部
    public List<GongYingShang> getAll();

    //条件查询
    public List<GongYingShang> queryList(String bianhao,String name);

    //增加
    public int gongyingshangAdd(GongYingShang gongYingShang);

    //修改
    public int gongyingshangUpd(GongYingShang gongYingShang);

    //根据id删除
    public int deleteById(List<Integer> list);

    //根据id查询
    public List<GongYingShang> selectById(Integer id);


    public List<GongYingShang> selectMaxDanHao();

}