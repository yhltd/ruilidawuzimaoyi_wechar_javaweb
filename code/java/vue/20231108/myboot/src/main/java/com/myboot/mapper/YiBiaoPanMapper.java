package com.myboot.mapper;

import com.myboot.pojo.KuCun;
import com.myboot.pojo.YiBiaoPan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YiBiaoPanMapper {

    //查询仪表盘 新增销售数据 订单部分
    List<YiBiaoPan> xinZengXiaoShouDingDanNum(String start_date, String stop_date);

    //查询仪表盘 新增销售数据 出库部分
    List<YiBiaoPan> xinZengXiaoShouChuKuNum(String start_date,String stop_date);

    //查询仪表盘 新增销售数据 收款部分
    List<YiBiaoPan> xinZengXiaoShouShouKuanNum(String start_date,String stop_date);

    //查询仪表盘 新增销售数据 毛利部分
    List<YiBiaoPan> xinZengXiaoShouMaoLiNum(String start_date,String stop_date);



    //查询仪表盘 客户欠款排行
    List<YiBiaoPan> keHuQianKuanPaiHang();

    //查询仪表盘 供应商欠款排行
    List<YiBiaoPan> gongYingShangQianKuanPaiHang();




    //查询仪表盘 销售订单排行 按业务员
    List<YiBiaoPan> xiaoShouDingDanPaiHang_YeWuYuan(String start_date,String stop_date);

    //查询仪表盘 销售订单排行 按客户
    List<YiBiaoPan> xiaoShouDingDanPaiHang_KeHu(String start_date,String stop_date);

    //查询仪表盘 销售订单排行 按商品
    List<YiBiaoPan> xiaoShouDingDanPaiHang_ShangPin(String start_date,String stop_date);




    //查询仪表盘 销售收款排行 按业务员
    List<YiBiaoPan> xiaoShouShouKuanPaiHang_YeWuYuan(String start_date,String stop_date);

    //查询仪表盘 销售收款排行 按客户
    List<YiBiaoPan> xiaoShouShouKuanPaiHang_KeHu(String start_date,String stop_date);




    //查询仪表盘 销售毛利排行 按业务员
    List<YiBiaoPan> xiaoShouMaoLiPaiHang_YeWuYuan(String start_date,String stop_date);

    //查询仪表盘 销售毛利排行 按客户
    List<YiBiaoPan> xiaoShouMaoLiPaiHang_KeHu(String start_date,String stop_date);

    //查询仪表盘 销售毛利排行 按商品
    List<YiBiaoPan> xiaoShouMaoLiPaiHang_ShangPin(String start_date,String stop_date);




    //查询仪表盘 销售订单走势 按月
    List<YiBiaoPan> xiaoShouDingDanZouShi_AnYue(String start_date,String stop_date);

    //查询仪表盘 销售订单走势 按年
    List<YiBiaoPan> xiaoShouDingDanZouShi_AnNian(String start_date,String stop_date);




    //查询仪表盘 销售收款走势 按月
    List<YiBiaoPan> xiaoShouShouKuanZouShi_AnYue(String start_date,String stop_date);

    //查询仪表盘 销售收款走势 按年
    List<YiBiaoPan> xiaoShouShouKuanZouShi_AnNian(String start_date,String stop_date);




    //查询仪表盘 采购付款走势 按月
    List<YiBiaoPan> caiGouFuKuanZouShi_AnYue(String start_date,String stop_date);

    //查询仪表盘 采购付款走势 按年
    List<YiBiaoPan> caiGouFuKuanZouShi_AnNian(String start_date,String stop_date);



    //销售毛利统计 按出货单
    List<YiBiaoPan> xiaoShouMaoLiTongJi_ChuHuoDan(String start_date,String stop_date);

    //销售毛利统计 按产品
    List<YiBiaoPan> xiaoShouMaoLiTongJi_ChanPin(String start_date,String stop_date);

    //销售毛利统计 按客户
    List<YiBiaoPan> xiaoShouMaoLiTongJi_KeHu(String start_date,String stop_date);

    //销售毛利统计 按业务员
    List<YiBiaoPan> xiaoShouMaoLiTongJi_YeWuYuan(String start_date,String stop_date);



    //销售订单统计 按产品
    List<YiBiaoPan> xiaoShouDingDanTongJi_ChanPin(String start_date,String stop_date);

    //销售订单统计 按客户
    List<YiBiaoPan> xiaoShouDingDanTongJi_KeHu(String start_date,String stop_date);

    //销售订单统计 按业务员
    List<YiBiaoPan> xiaoShouDingDanTongJi_YeWuYuan(String start_date,String stop_date);



    //销售收款统计 按客户
    List<YiBiaoPan> xiaoShouShouKuanTongJi_KeHu(String start_date,String stop_date);

    //销售收款统计 按业务员
    List<YiBiaoPan> xiaoShouShouKuanTongJi_YeWuYuan(String start_date,String stop_date);



    //销售订单走势 按日
    List<YiBiaoPan> xiaoShouDingDanZouShi_Ri(String riqi);

    //销售订单走势 按月
    List<YiBiaoPan> xiaoShouDingDanZouShi_Yue(String riqi);


    //销售收款走势 按日
    List<YiBiaoPan> xiaoShouShouKuanZouShi_Ri(String riqi);

    //销售收款走势 按月
    List<YiBiaoPan> xiaoShouShouKuanZouShi_Yue(String riqi);


    //采购付款走势 按日
    List<YiBiaoPan> caiGouFuKuanZouShi_Ri(String riqi);

    //采购付款走势 按月
    List<YiBiaoPan> caiGouFuKuanZouShi_Yue(String riqi);


    //客户往来
    List<YiBiaoPan> keHuWangLai(String start_date,String stop_date);

    //供应商往来
    List<YiBiaoPan> gongYingShangWangLai(String start_date,String stop_date);


}
