package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.CaiGouShouPiao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaiGouShouPiaoService {

    //查询全部
    public List<CaiGouShouPiao> getAll();

    //查询需要自己审核
    public List<CaiGouShouPiao> getAllByShenHe(String xinxi_tuisong);

    //条件查询
    public List<CaiGouShouPiao> queryList(String start_date,String stop_date,String kaipiao_danwei,String shoupiao_danwei,String shoupiao_zhuangtai);

    //增加
    public List<CaiGouShouPiao> caiGouShouPiaoAdd(String caigou_bianhao,String shoupiao_danwei,String kaipiao_danwei,String kaipiao_riqi,String kaipiao_jine,String kaipiao_shuie,String jiashui_heji,String beizhu,String xinxi_tuisong,String shoupiao_zhuangtai);

    //修改
    public List<CaiGouShouPiao> caiGouShouPiaoUpd(String caigou_bianhao,String shoupiao_danwei,String kaipiao_danwei,String kaipiao_riqi,String kaipiao_jine,String kaipiao_shuie,String jiashui_heji,String beizhu,String xinxi_tuisong,String shoupiao_zhuangtai,Integer id);

    //根据采购id删除
    public boolean deleteByShouPiaoId(Integer id);

    //根据采购id查询
    public List<CaiGouShouPiao> selectByShouPiaoId(Integer id);

}