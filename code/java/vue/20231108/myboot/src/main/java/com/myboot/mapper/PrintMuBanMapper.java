package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.PrintMuBan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrintMuBanMapper {

    //查询全部
    public List<PrintMuBan> getAll();

    //条件查询
    public List<PrintMuBan> queryList(String name);


    public List<PrintMuBan> getMuBanByType(String type);

    //增加
    public int muBanAdd(PrintMuBan printMuBan);

    //修改
    public int muBanUpd(PrintMuBan printMuBan);

    //根据采购id删除
    public int delById(@Param("id") Integer id);

    public List<PrintMuBan> getMuBanById(Integer id);

}
