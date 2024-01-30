package com.myboot.service.impl;

import com.myboot.mapper.KuCunMapper;
import com.myboot.mapper.YiBiaoPanMapper;
import com.myboot.pojo.KuCun;
import com.myboot.pojo.YiBiaoPan;
import com.myboot.service.KuCunService;
import com.myboot.service.YiBiaoPanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YiBiaoPanImpl implements YiBiaoPanService {

    @Autowired
    private YiBiaoPanMapper mapper;

    @Override
    public List<YiBiaoPan> xinZengXiaoShouDingDanNum(String start_date, String stop_date) {
        return mapper.xinZengXiaoShouDingDanNum(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xinZengXiaoShouChuKuNum(String start_date, String stop_date) {
        return mapper.xinZengXiaoShouChuKuNum(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xinZengXiaoShouShouKuanNum(String start_date, String stop_date) {
        return mapper.xinZengXiaoShouShouKuanNum(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xinZengXiaoShouMaoLiNum(String start_date, String stop_date) {
        return mapper.xinZengXiaoShouMaoLiNum(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> keHuQianKuanPaiHang() {
        return mapper.keHuQianKuanPaiHang();
    }

    @Override
    public List<YiBiaoPan> gongYingShangQianKuanPaiHang() {
        return mapper.gongYingShangQianKuanPaiHang();
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanPaiHang_YeWuYuan(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanPaiHang_YeWuYuan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanPaiHang_KeHu(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanPaiHang_KeHu(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanPaiHang_ShangPin(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanPaiHang_ShangPin(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanPaiHang_YeWuYuan(String start_date, String stop_date) {
        return mapper.xiaoShouShouKuanPaiHang_YeWuYuan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanPaiHang_KeHu(String start_date, String stop_date) {
        return mapper.xiaoShouShouKuanPaiHang_KeHu(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiPaiHang_YeWuYuan(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiPaiHang_YeWuYuan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiPaiHang_KeHu(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiPaiHang_KeHu(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiPaiHang_ShangPin(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiPaiHang_ShangPin(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanZouShi_AnYue(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanZouShi_AnYue(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanZouShi_AnNian(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanZouShi_AnNian(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanZouShi_AnYue(String start_date, String stop_date) {
        return mapper.xiaoShouShouKuanZouShi_AnYue(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanZouShi_AnNian(String start_date, String stop_date) {
        return mapper.xiaoShouShouKuanZouShi_AnNian(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> caiGouFuKuanZouShi_AnYue(String start_date, String stop_date) {
        return mapper.caiGouFuKuanZouShi_AnYue(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> caiGouFuKuanZouShi_AnNian(String start_date, String stop_date) {
        return mapper.caiGouFuKuanZouShi_AnNian(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiTongJi_ChuHuoDan(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiTongJi_ChuHuoDan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiTongJi_ChanPin(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiTongJi_ChanPin(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiTongJi_KeHu(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiTongJi_KeHu(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiTongJi_YeWuYuan(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiTongJi_YeWuYuan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiTongJi_DianPu(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiTongJi_DianPu(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiTongJi_HeSuanDanWei(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiTongJi_HeSuanDanWei(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouMaoLiTongJi_XiaoShouDingDan(String start_date, String stop_date) {
        return mapper.xiaoShouMaoLiTongJi_XiaoShouDingDan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanTongJi_ChanPin(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanTongJi_ChanPin(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanTongJi_KeHu(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanTongJi_KeHu(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanTongJi_YeWuYuan(String start_date, String stop_date) {
        return mapper.xiaoShouDingDanTongJi_YeWuYuan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanTongJi_KeHu(String start_date, String stop_date) {
        return mapper.xiaoShouShouKuanTongJi_KeHu(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanTongJi_YeWuYuan(String start_date, String stop_date) {
        return mapper.xiaoShouShouKuanTongJi_YeWuYuan(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanZouShi_Ri(String riqi) {
        return mapper.xiaoShouDingDanZouShi_Ri(riqi);
    }

    @Override
    public List<YiBiaoPan> xiaoShouDingDanZouShi_Yue(String riqi) {
        return mapper.xiaoShouDingDanZouShi_Yue(riqi);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanZouShi_Ri(String riqi) {
        return mapper.xiaoShouShouKuanZouShi_Ri(riqi);
    }

    @Override
    public List<YiBiaoPan> xiaoShouShouKuanZouShi_Yue(String riqi) {
        return mapper.xiaoShouShouKuanZouShi_Yue(riqi);
    }

    @Override
    public List<YiBiaoPan> caiGouFuKuanZouShi_Ri(String riqi) {
        return mapper.caiGouFuKuanZouShi_Ri(riqi);
    }

    @Override
    public List<YiBiaoPan> caiGouFuKuanZouShi_Yue(String riqi) {
        return mapper.caiGouFuKuanZouShi_Yue(riqi);
    }

    @Override
    public List<YiBiaoPan> keHuWangLai(String start_date, String stop_date) {
        return mapper.keHuWangLai(start_date,stop_date);
    }

    @Override
    public List<YiBiaoPan> gongYingShangWangLai(String start_date, String stop_date) {
        return mapper.gongYingShangWangLai(start_date,stop_date);
    }
}
