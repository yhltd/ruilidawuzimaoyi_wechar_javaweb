package com.myboot.service;

import com.myboot.pojo.PeiZhiGuiGe;

import java.util.List;

public interface PeiZhiGuiGeService {
//    查询全部
    public List<PeiZhiGuiGe> getAll();

//    条件查询
    public List<PeiZhiGuiGe> selectByProduct(String type,String guige);

//    商品规格增加
    public int peiZhiGuiGeAdd(PeiZhiGuiGe peiZhiGuiGe);

//    修改
    public int peiZhiGuiGeUpd(PeiZhiGuiGe peiZhiGuiGe);

//根据商品规格id删除
    public int delByPeiZhiGuiGeId(Integer id);

//    删除
    int delPeiZhiGuiGe(List<Integer> list);

    //    查询重复
    public List<PeiZhiGuiGe> getChongFu(String this_where);
}
