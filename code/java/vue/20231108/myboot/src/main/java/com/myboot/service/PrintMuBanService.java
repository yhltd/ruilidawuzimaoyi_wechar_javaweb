package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.PrintMuBan;

import java.util.List;

public interface PrintMuBanService {

    //查询全部
    public List<PrintMuBan> getAll();

    //查询
    public List<PrintMuBan> queryList(String name);

    //查询
    public List<PrintMuBan> getMuBanById(Integer id);

    //查询
    public List<PrintMuBan> getMuBanByType(String type);

    //增加
    public int muBanAdd(PrintMuBan printMuBan);

    //修改
    public int muBanUpd(PrintMuBan printMuBan);

    //根据采购id删除
    int delById(List<Integer> list);

}