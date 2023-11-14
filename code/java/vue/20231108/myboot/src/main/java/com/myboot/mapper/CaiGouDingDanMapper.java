package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouDingDanAdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouDingDanMapper {

    //查询全部
    public List<CaiGouDingDan> getAll();

    //查询需要自己审核
    public List<CaiGouDingDan> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<CaiGouDingDan> queryList(String start_date,String stop_date,String gongyingshang,String shenhe_zhuangtai);

    //增加
    public int caiGouDingDanAdd(CaiGouDingDan caiGouDingDan);

    //修改
    public int caiGouDingDanUpd(CaiGouDingDan caiGouDingDan);

    //根据采购id删除
    public int deleteByCaiGouId(@Param("id") Integer id);

    //根据采购id查询
    public List<CaiGouDingDan> selectByCaiGouId(@Param("id") Integer id);

    //查询最大单号
    public List<CaiGouDingDan> selectMaxDanHao();

    int deleteUsrById(Integer id);

    public List<CaiGouDingDan> selectWeiFu();


}
