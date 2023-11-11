package com.myboot.service;

import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouDingDan;

import java.util.List;

public interface XiaoShouDingDanService {

    //查询全部
    public List<XiaoShouDingDan> getAll();

    //查询需要自己审核
    public List<XiaoShouDingDan> getAllByShenHe(String shenhe);

    //条件查询
    public List<XiaoShouDingDan> queryList(String start_date,String stop_date,String kehu, String shenhe_zhuangtai);

    //增加
    public List<XiaoShouDingDan> xiaoShouAdd(String bianhao,String riqi,String kehu, String yewuyuan, String shoujianren, String shoujian_phone, String shoujian_dizhi, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String shenhe_zhuangtai, String jiashui_heji, String jiage_dengji);

    //修改
    public List<XiaoShouDingDan> xiaoShouUpd(String bianhao,String riqi,String kehu, String yewuyuan, String shoujianren, String shoujian_phone, String shoujian_dizhi, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String shenhe_zhuangtai, String jiashui_heji, String jiage_dengji,Integer id);

    //根据采购id删除
    public boolean deleteXiaoShouById(Integer id);

    //根据采购id查询
    public List<XiaoShouDingDan> selectXiaoShouById(Integer id);

}