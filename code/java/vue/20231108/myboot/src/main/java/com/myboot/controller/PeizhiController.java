package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.Peizhi;
import com.myboot.pojo.PeizhiTypeEnum;
import com.myboot.service.PeizhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hui
 * @date 2023/11/8 11:17
 */
@RestController
@RequestMapping("/peizhi")
public class PeizhiController {

    @Autowired
    private PeizhiService peizhiService;

    //仓库
    @PostMapping("/queryPeiZhi")
    public String queryPeiZhi(@RequestBody JSONObject data) {
        String type = data.getString("type");
        List<Peizhi> list = peizhiService.queryAllByType(type);
        return ResponseCommon.success(list);
    }

//    //仓库
//    @PostMapping("/queryAllCangKu")
//    public String queryAllCangku() {
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.CANG_KU);
//        return ResponseCommon.success(list);
//    }
//
//    //收款账户
//    @PostMapping("/queryAllShoukuanZhanghu")
//    public String queryAllShoukuanZhanghu(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.SHOU_KUAN_ZHNAG_HU);
//        return ResponseCommon.success(list);
//    }
//
//    //店铺
//    @PostMapping("/queryAllDianPu")
//    public String queryAllDianpu(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.DIAN_PU);
//        return ResponseCommon.success(list);
//    }
//
//    //核算单位
//    @PostMapping("/queryAllHesuanDanwei")
//    public String queryAllHesuanDanwei(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.HE_SUAN_DAN_WEI);
//        return ResponseCommon.success(list);
//    }
//
//    //客户等级
//    @PostMapping("/queryAllKehuDengji")
//    public String queryAllKehuDengji(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.KE_HU_LEVEL);
//        return ResponseCommon.success(list);
//    }
//
//    //供应商等级
//    @PostMapping("/queryAllGongyingshangDengji")
//    public String queryAllGongyingshangDengji(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.GONG_YING_SHANG_LEVEL);
//        return ResponseCommon.success(list);
//    }
//
//    //价格等级
//    @PostMapping("/queryAllJiageDengji")
//    public String queryAllJiageDengji(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.JIA_GE_LEVEL);
//        return ResponseCommon.success(list);
//    }
//
//    //客户分类
//    @PostMapping("/queryAllKehuFenlei")
//    public String queryAllKehuFenlei(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.KE_HU_TYPE);
//        return ResponseCommon.success(list);
//    }
//
//    //质保等级
//    @PostMapping("/queryAllZhibaoDengjI")
//    public String queryAllZhibaoDengjI(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.ZHI_BAO_LEVEL);
//        return ResponseCommon.success(list);
//    }
//
//    //价格上浮率
//    @PostMapping("/queryAllJiageShangfu")
//    public String queryAllJiageShangfu(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.JIA_GE_SHANGFU);
//        return ResponseCommon.success(list);
//    }
//
//    //记账分类
//    @PostMapping("/queryAllJizhangFenlei")
//    public String queryAllJizhangFenlei(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.JI_ZHANG_TYPE);
//        return ResponseCommon.success(list);
//    }
//
//    //记账明细类型
//    @PostMapping("/queryAllJizhangMingxiType")
//    public String queryAllJizhangMingxiType(){
//        List<Peizhi> list = peizhiService.queryAllByType(PeizhiTypeEnum.JI_ZHANG_MING_XI_TYPE);
//        return ResponseCommon.success(list);
//    }

    @PostMapping("/peizhiAdd")
    public String peizhiAdd(@RequestBody Peizhi peizhi) {
        Integer result = peizhiService.peiZhiAdd(peizhi);
        return ResponseCommon.success(peizhi);
    }

    @PostMapping("/peizhiUpd")
    public String peizhiUpd(@RequestBody Peizhi peizhi) {
        Integer result = peizhiService.peiZhiUpd(peizhi);
        return ResponseCommon.success(peizhi);
    }

    @PostMapping("/selectByPeiZhiId")
    public String selectByPeiZhiId(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }

        List<Peizhi> peizhi = peizhiService.selectByPeiZhiId(data.getInteger("id"));
        if (peizhi == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未查询到配置项");
        }
        return ResponseCommon.success(peizhi);
    }

    @RequestMapping("/delPeizhi")
    public String delPeizhi(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = peizhiService.delUsers(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }
}
