package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CaiGouDingDanService {

    //查询全部
    public List<CaiGouDingDan> getAll();

    //查询需要自己审核
    public List<CaiGouDingDan> getAllByShenHe(String shenhe);

    //条件查询
    public List<CaiGouDingDan> queryList(String start_date,String stop_date,String gongyingshang, String shenhe_zhuangtai);

    //增加
    public List<CaiGouDingDan> caiGouDingDanAdd(String bianhao,String riqi,String gongyingshang, String dianpu, String jinxiangShuilv,String beizhu,String shenhe, String shenhe_zhuangtai);

    //修改
    public List<CaiGouDingDan> caiGouDingDanUpd(String bianhao,String riqi,String gongyingshang,String dianpu,String jinxiang_shuilv,String beizhu,String shenhe,String shenhe_zhuangtai,Integer id);

    //根据采购id删除
    public boolean deleteByCaiGouId(Integer id);

    //根据采购id查询
    public List<CaiGouDingDan> selectByCaiGouId(Integer id);

}