package com.myboot.service;

import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.pojo.XiaoShouKaiPiao;

import java.util.List;

public interface XiaoShouKaiPiaoService {

    //查询全部
    public List<XiaoShouKaiPiao> getAll();

    //查询需要自己审核
    public List<XiaoShouKaiPiao> getAllByShenHe(String xinxi_tuisong);

    //条件查询
    public List<XiaoShouKaiPiao> queryList(String start_date,String stop_date,String shoupiao_danwei, String kaipiao_danwei,String kaipiao_zhuangtai);

    //增加
    public List<XiaoShouKaiPiao> kaiPiaoAdd(String xiaoshou_bianhao,String shoupiao_danwei,String shibiehao, String kaipiao_dizhi, String kaipiao_dianhua, String kaipiao_yinhang, String kaipiao_zhanghao, String kaipiao_riqi,String kaipiao_jine,String kaipiao_shuie, String jiashui_heji, String beizhu, String kaipiao_danwei, String xinxi_tuisong, String kaipiao_zhuangtai);

    //修改
    public List<XiaoShouKaiPiao> kaiPiaoUpd(String xiaoshou_bianhao,String shoupiao_danwei,String shibiehao, String kaipiao_dizhi, String kaipiao_dianhua, String kaipiao_yinhang, String kaipiao_zhanghao, String kaipiao_riqi,String kaipiao_jine,String kaipiao_shuie, String jiashui_heji, String beizhu, String kaipiao_danwei, String xinxi_tuisong, String kaipiao_zhuangtai,Integer id);

    //根据采购id删除
    public boolean deleteXiaoShouById(Integer id);

    //根据采购id查询
    public List<XiaoShouKaiPiao> selectXiaoShouById(Integer id);

}