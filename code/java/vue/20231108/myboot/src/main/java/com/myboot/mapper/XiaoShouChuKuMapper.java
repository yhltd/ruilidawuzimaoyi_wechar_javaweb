package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouChuKu;
import com.myboot.pojo.XiaoShouChuKuXiangQing;
import com.myboot.pojo.XiaoShouDingDanXiangQing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouChuKuMapper {

    //查询全部
    public List<XiaoShouChuKu> getAll();

    //查询需要自己审核
    public List<XiaoShouChuKu> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<XiaoShouChuKu> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu);

    //增加
    public int chuKuAdd(XiaoShouChuKu xiaoShouChuKu);

    //修改
    public int chuKuUpd(XiaoShouChuKu xiaoShouChuKu);

    //根据采购id删除
    public int deleteByChuKuId(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouChuKu> selectByChuKuId(@Param("id") Integer id);

    public List<XiaoShouChuKu> selectMaxDanHao();

    public List<XiaoShouChuKu> selectWeiFu();

    public List<XiaoShouChuKuXiangQing> xiangQingShangPin(Integer id);

    public List<XiaoShouChuKuXiangQing> xiangQingShouKuan(String bianhao);

    public List<XiaoShouChuKuXiangQing> xiangQingKaiPiao(String bianhao);

    public List<XiaoShouChuKuXiangQing> xiangQingDingJin(String bianhao);

    public List<XiaoShouChuKuXiangQing> xiangQingDingJinYiYong(String bianhao);

    //修改使用订金金额
    public int chuKuDingJinUpd(Integer id,String dingjin);

    public List<XiaoShouChuKuXiangQing> xiangQingXuKaiPiao(String bianhao);
}
