package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.XiaoShouBaoJia;

import java.util.List;

public interface XiaoShouBaoJiaService {

    //查询全部
    public List<XiaoShouBaoJia> getAll();

    //查询需要自己审核
    public List<XiaoShouBaoJia> getAllByShenHe(String shenhe);

    //条件查询
    public List<XiaoShouBaoJia> queryList(String start_date,String stop_date,String kehu, String shenhe_zhuangtai);

    //增加
    public List<XiaoShouBaoJia> baoJiaAdd(String bianhao,String riqi,String kehu, String yewuyuan, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String jiage_dengji, String shenhe_zhuangtai);

    //修改
    public List<XiaoShouBaoJia> baoJiaUpd(String bianhao,String riqi,String kehu, String yewuyuan, String dianpu,String xiaoxiang_shuilv,String beizhu, String shenhe, String jiage_dengji, String shenhe_zhuangtai,Integer id);

    //根据采购id删除
    public boolean deleteBaoJiaById(Integer id);

    //根据采购id查询
    public List<XiaoShouBaoJia> selectBaoJiaById(Integer id);

}