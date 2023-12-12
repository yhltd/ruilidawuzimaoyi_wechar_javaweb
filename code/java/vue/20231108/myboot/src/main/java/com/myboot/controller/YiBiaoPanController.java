package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.KuCun;
import com.myboot.pojo.YiBiaoPan;
import com.myboot.service.KuCunService;
import com.myboot.service.YiBiaoPanService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yiBiaoPan")
public class YiBiaoPanController {
    @Autowired
    private YiBiaoPanService service;

    //查询仪表盘 新增销售数据 订单部分
    @PostMapping("/xinZengXiaoShouDingDanNum")
    public String getKuCunByCangKu(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xinZengXiaoShouDingDanNum(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 新增销售数据 出库部分
    @PostMapping("/xinZengXiaoShouChuKuNum")
    public String xinZengXiaoShouChuKuNum(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xinZengXiaoShouChuKuNum(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 新增销售数据 收款部分
    @PostMapping("/xinZengXiaoShouShouKuanNum")
    public String xinZengXiaoShouShouKuanNum(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xinZengXiaoShouShouKuanNum(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 新增销售数据 毛利部分
    @PostMapping("/xinZengXiaoShouMaoLiNum")
    public String xinZengXiaoShouMaoLiNum(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xinZengXiaoShouMaoLiNum(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 客户欠款排行
    @PostMapping("/keHuQianKuanPaiHang")
    public String keHuQianKuanPaiHang(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.keHuQianKuanPaiHang();
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 供应商欠款排行
    @PostMapping("/gongYingShangQianKuanPaiHang")
    public String gongYingShangQianKuanPaiHang(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.gongYingShangQianKuanPaiHang();
        return ResponseCommon.success(yiBiaoPans);
    }


    //查询仪表盘 销售订单排行 按业务员
    @PostMapping("/xiaoShouDingDanPaiHang_YeWuYuan")
    public String xiaoShouDingDanPaiHang_YeWuYuan(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanPaiHang_YeWuYuan(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售订单排行 按客户
    @PostMapping("/xiaoShouDingDanPaiHang_KeHu")
    public String xiaoShouDingDanPaiHang_KeHu(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanPaiHang_KeHu(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售订单排行 按商品
    @PostMapping("/xiaoShouDingDanPaiHang_ShangPin")
    public String xiaoShouDingDanPaiHang_ShangPin(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanPaiHang_ShangPin(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售收款排行 按业务员
    @PostMapping("/xiaoShouShouKuanPaiHang_YeWuYuan")
    public String xiaoShouShouKuanPaiHang_YeWuYuan(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanPaiHang_YeWuYuan(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售收款排行 按客户
    @PostMapping("/xiaoShouShouKuanPaiHang_KeHu")
    public String xiaoShouShouKuanPaiHang_KeHu(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanPaiHang_KeHu(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售毛利排行 按业务员
    @PostMapping("/xiaoShouMaoLiPaiHang_YeWuYuan")
    public String xiaoShouMaoLiPaiHang_YeWuYuan(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouMaoLiPaiHang_YeWuYuan(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售毛利排行 按客户
    @PostMapping("/xiaoShouMaoLiPaiHang_KeHu")
    public String xiaoShouMaoLiPaiHang_KeHu(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouMaoLiPaiHang_KeHu(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售毛利排行 按商品
    @PostMapping("/xiaoShouMaoLiPaiHang_ShangPin")
    public String xiaoShouMaoLiPaiHang_ShangPin(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouMaoLiPaiHang_ShangPin(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }


    //查询仪表盘 销售订单走势 按月
    @PostMapping("/xiaoShouDingDanZouShi_AnYue")
    public String xiaoShouDingDanZouShi_AnYue(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanZouShi_AnYue(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售订单走势 按年
    @PostMapping("/xiaoShouDingDanZouShi_AnNian")
    public String xiaoShouDingDanZouShi_AnNian(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanZouShi_AnNian(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }


    //查询仪表盘 销售收款走势 按月
    @PostMapping("/xiaoShouShouKuanZouShi_AnYue")
    public String xiaoShouShouKuanZouShi_AnYue(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanZouShi_AnYue(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 销售收款走势 按年
    @PostMapping("/xiaoShouShouKuanZouShi_AnNian")
    public String xiaoShouShouKuanZouShi_AnNian(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanZouShi_AnNian(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }


    //查询仪表盘 采购付款走势 按月
    @PostMapping("/caiGouFuKuanZouShi_AnYue")
    public String caiGouFuKuanZouShi_AnYue(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.caiGouFuKuanZouShi_AnYue(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //查询仪表盘 采购付款走势 按年
    @PostMapping("/caiGouFuKuanZouShi_AnNian")
    public String caiGouFuKuanZouShi_AnNian(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.caiGouFuKuanZouShi_AnNian(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }



    //销售毛利统计 按出货单
    @PostMapping("/xiaoShouMaoLiTongJi_ChuHuoDan")
    public String xiaoShouMaoLiTongJi_ChuHuoDan(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouMaoLiTongJi_ChuHuoDan(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售毛利统计 按产品
    @PostMapping("/xiaoShouMaoLiTongJi_ChanPin")
    public String xiaoShouMaoLiTongJi_ChanPin(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouMaoLiTongJi_ChanPin(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }


    //销售毛利统计 按客户
    @PostMapping("/xiaoShouMaoLiTongJi_KeHu")
    public String xiaoShouMaoLiTongJi_KeHu(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouMaoLiTongJi_KeHu(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售毛利统计 按业务员
    @PostMapping("/xiaoShouMaoLiTongJi_YeWuYuan")
    public String xiaoShouMaoLiTongJi_YeWuYuan(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouMaoLiTongJi_YeWuYuan(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }


    //销售订单统计 按产品
    @PostMapping("/xiaoShouDingDanTongJi_ChanPin")
    public String xiaoShouDingDanTongJi_ChanPin(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanTongJi_ChanPin(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售订单统计 按客户
    @PostMapping("/xiaoShouDingDanTongJi_KeHu")
    public String xiaoShouDingDanTongJi_KeHu(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanTongJi_KeHu(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售订单统计 按业务员
    @PostMapping("/xiaoShouDingDanTongJi_YeWuYuan")
    public String xiaoShouDingDanTongJi_YeWuYuan(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanTongJi_YeWuYuan(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }


    //销售收款统计 按客户
    @PostMapping("/xiaoShouShouKuanTongJi_KeHu")
    public String xiaoShouShouKuanTongJi_KeHu(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanTongJi_KeHu(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售收款统计 按业务员
    @PostMapping("/xiaoShouShouKuanTongJi_YeWuYuan")
    public String xiaoShouShouKuanTongJi_YeWuYuan(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanTongJi_YeWuYuan(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }


    //销售订单走势 按日
    @PostMapping("/xiaoShouDingDanZouShi_Ri")
    public String xiaoShouDingDanZouShi_Ri(@RequestBody JSONObject data) {
        String riqi = data.getString("riqi");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanZouShi_Ri(riqi);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售订单走势 按月
    @PostMapping("/xiaoShouDingDanZouShi_Yue")
    public String xiaoShouDingDanZouShi_Yue(@RequestBody JSONObject data) {
        String riqi = data.getString("riqi");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouDingDanZouShi_Yue(riqi);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售收款走势 按日
    @PostMapping("/xiaoShouShouKuanZouShi_Ri")
    public String xiaoShouShouKuanZouShi_Ri(@RequestBody JSONObject data) {
        String riqi = data.getString("riqi");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanZouShi_Ri(riqi);
        return ResponseCommon.success(yiBiaoPans);
    }

    //销售收款走势 按月
    @PostMapping("/xiaoShouShouKuanZouShi_Yue")
    public String xiaoShouShouKuanZouShi_Yue(@RequestBody JSONObject data) {
        String riqi = data.getString("riqi");
        List<YiBiaoPan> yiBiaoPans = service.xiaoShouShouKuanZouShi_Yue(riqi);
        return ResponseCommon.success(yiBiaoPans);
    }


    //采购付款走势 按日
    @PostMapping("/caiGouFuKuanZouShi_Ri")
    public String caiGouFuKuanZouShi_Ri(@RequestBody JSONObject data) {
        String riqi = data.getString("riqi");
        List<YiBiaoPan> yiBiaoPans = service.caiGouFuKuanZouShi_Ri(riqi);
        return ResponseCommon.success(yiBiaoPans);
    }

    //采购付款走势 按月
    @PostMapping("/caiGouFuKuanZouShi_Yue")
    public String caiGouFuKuanZouShi_Yue(@RequestBody JSONObject data) {
        String riqi = data.getString("riqi");
        List<YiBiaoPan> yiBiaoPans = service.caiGouFuKuanZouShi_Yue(riqi);
        return ResponseCommon.success(yiBiaoPans);
    }



    //客户往来
    @PostMapping("/keHuWangLai")
    public String keHuWangLai(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.keHuWangLai(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

    //供应商往来
    @PostMapping("/gongYingShangWangLai")
    public String gongYingShangWangLai(@RequestBody JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        List<YiBiaoPan> yiBiaoPans = service.gongYingShangWangLai(start_date,stop_date);
        return ResponseCommon.success(yiBiaoPans);
    }

}
