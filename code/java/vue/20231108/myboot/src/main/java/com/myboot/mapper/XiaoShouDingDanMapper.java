package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.pojo.XiaoShouDingDanAdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouDingDanMapper {

    //查询全部
    public List<XiaoShouDingDan> getAll();

    public List<XiaoShouDingDan> getAllByName(String yewuyuan);

    //查询需要自己审核
    public List<XiaoShouDingDan> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<XiaoShouDingDan> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    public List<XiaoShouDingDan> queryListByName(@Param("yewuyuan") String yewuyuan,@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    //增加
    public int xiaoShouAdd(XiaoShouDingDan xiaoShouDingDan);

    //修改
    public int xiaoShouUpd(XiaoShouDingDan xiaoShouDingDan);

    //根据采购id删除
    public int deleteXiaoShouById(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouDingDan> selectXiaoShouById(@Param("id") Integer id);

    public List<XiaoShouDingDan> selectWeiFu();

    public List<XiaoShouDingDan> selectMaxDanHao();

    public List<XiaoShouDingDan> shenHeList(String name);

    public int dingDanShenHe(int id,String type);


}
