package com.myboot.service;

import com.myboot.pojo.*;

import java.util.List;

public interface XiaoShouDingDanService {

    //查询全部
    public List<XiaoShouDingDan> getAll();

    public List<XiaoShouDingDan> getAllByName(String yewuyuan);

    //查询需要自己审核
    public List<XiaoShouDingDan> getAllByShenHe(String shenhe);

    //条件查询
    public List<XiaoShouDingDan> queryList(String start_date,String stop_date,String kehu, String shenhe_zhuangtai);

    public List<XiaoShouDingDan> queryListByName(String yewuyuan,String start_date,String stop_date,String kehu, String shenhe_zhuangtai);

    //增加
    public int xiaoShouAdd(XiaoShouDingDan xiaoShouDingDan);

    //修改
    public int xiaoShouUpd(XiaoShouDingDan xiaoShouDingDan);

    //根据采购id删除
    public int deleteXiaoShouById(Integer id);

    //根据采购id查询
    public List<XiaoShouDingDan> selectXiaoShouById(Integer id);

    int delUsers(List<Integer> list);

    public int xiaoShouShenHe(List<Integer> list,String type);

    public List<XiaoShouDingDan> selectWeiFu();

    public List<XiaoShouDingDan> selectMaxDanHao();

    public List<XiaoShouDingDan> shenHeList(String name);

    public List<XiaoShouDingDanXiangQing> xiangQingShangPin(Integer id,String bianhao);

    public List<XiaoShouDingDanXiangQing> xiangQingChuKu(String bianhao);

    public List<XiaoShouDingDanXiangQing> xiangQingShouKuan(String bianhao);

    public List<XiaoShouDingDanXiangQing> xiangQingKaiPiao(String bianhao);

    public List<XiaoShouDingDanXiangQing> xiangQingCaiGou(String bianhao);

    public List<XiaoShouDingDanXiangQing> xiangQingXuKaiPiao(String bianhao);

}