package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.CaiGouShouPiaoService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/caiGouDingDan")
public class CaiGouDingDanController {
    @Autowired
    private CaiGouDingDanService service;

    @Autowired
    private CaiGouDingDanItemService itemService;

    @GetMapping("/getAll")
    public String getAll(HttpSession session) {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(HttpSession session,@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String gongyingshang = data.getString("gongyingshang");
        String shenhe_zhuangtai = data.getString("shenhe_zhuangtai");
        List<CaiGouDingDan> caiGouDingDan = service.queryList(start_date,stop_date,gongyingshang,shenhe_zhuangtai);
        return ResponseCommon.success(caiGouDingDan);
    }

    @PostMapping("/getShenHe")
    public String getShenHe(HttpSession session,@RequestBody @NonNull JSONObject data) {
        String shenhe = data.getString("shenhe");
        List<CaiGouDingDan> caiGouDingDan = service.getAllByShenHe(shenhe);
        return ResponseCommon.success(caiGouDingDan);
    }

    @PostMapping("/selectWeiFu")
    public String selectWeiFu(HttpSession session,@RequestBody @NonNull JSONObject data) {
        List<CaiGouDingDan> caiGouDingDan = service.selectWeiFu();
        return ResponseCommon.success(caiGouDingDan);
    }

    @PostMapping("/selectMaxDanHao")
    public String selectMaxDanHao(HttpSession session,@RequestBody @NonNull JSONObject data) {
        List<CaiGouDingDan> caiGouDingDan = service.selectMaxDanHao();
        return ResponseCommon.success(caiGouDingDan);
    }

    @PostMapping("/caiGouDingDanAdd")
    public String caiGouDingDanAdd(HttpSession session,@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        CaiGouDingDan caiGouDingDan = data.getObject("head", CaiGouDingDan.class);
        List<CaiGouDingDanItem> caiGouDingDanItems = body.toJavaList(CaiGouDingDanItem.class);
        CaiGouDingDan dingDan = new CaiGouDingDan();
        BeanUtils.copyProperties(caiGouDingDan, dingDan);
        Integer this_id = service.caiGouDingDanAdd(dingDan);
        for(int i=0; i<caiGouDingDanItems.size(); i++){
            caiGouDingDanItems.get(i).setCaigouId(dingDan.getId());
            int result2 = itemService.caiGouDingDanItemAdd(caiGouDingDanItems.get(i));
        }
        return ResponseCommon.success(caiGouDingDan);
    }

    @PostMapping("/caiGouDingDanUpd")
    public String caiGouDingDanUpd(HttpSession session,@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        CaiGouDingDan caiGouDingDan = data.getObject("head", CaiGouDingDan.class);
        List<CaiGouDingDanItem> caiGouDingDanItems = body.toJavaList(CaiGouDingDanItem.class);
        CaiGouDingDan dingDan = new CaiGouDingDan();
        BeanUtils.copyProperties(caiGouDingDan, dingDan);
        Integer this_id = service.caiGouDingDanUpd(dingDan);
        itemService.deleteByCaiGouId(dingDan.getId());
        for(int i=0; i<caiGouDingDanItems.size(); i++){
            caiGouDingDanItems.get(i).setCaigouId(dingDan.getId());
            int result2 = itemService.caiGouDingDanItemAdd(caiGouDingDanItems.get(i));
        }
        return ResponseCommon.success(caiGouDingDan);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectByCaiGouId")
    public String selectByCaiGouId(HttpSession session,@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        Integer id = data.getInteger("id");
        List<CaiGouDingDan> caiGouDingDan = service.selectByCaiGouId(data.getInteger("id"));
        CaiGouDingDanAdd caiGouDingDanAdd = new CaiGouDingDanAdd();
        caiGouDingDanAdd.setId(caiGouDingDan.get(0).getId());
        caiGouDingDanAdd.setBianhao(caiGouDingDan.get(0).getBianhao());
        caiGouDingDanAdd.setRiqi(caiGouDingDan.get(0).getRiqi());
        caiGouDingDanAdd.setGongyingshang(caiGouDingDan.get(0).getGongyingshang());
        caiGouDingDanAdd.setDianpu(caiGouDingDan.get(0).getDianpu());
        caiGouDingDanAdd.setJinxiangShuilv(caiGouDingDan.get(0).getJinxiangShuilv());
        caiGouDingDanAdd.setBeizhu(caiGouDingDan.get(0).getBeizhu());
        caiGouDingDanAdd.setShenhe(caiGouDingDan.get(0).getShenhe());
        caiGouDingDanAdd.setShenheZhuangtai(caiGouDingDan.get(0).getShenheZhuangtai());
        caiGouDingDanAdd.setRukuZhuangtai(caiGouDingDan.get(0).getRukuZhuangtai());
        caiGouDingDanAdd.setShoupiaoZhuangtai(caiGouDingDan.get(0).getShoupiaoZhuangtai());
        caiGouDingDanAdd.setFukuanZhuangtai(caiGouDingDan.get(0).getFukuanZhuangtai());
        caiGouDingDanAdd.setProductList(itemService.selectByCaiGouId(data.getInteger("id")));

        if (caiGouDingDan == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(caiGouDingDanAdd);
    }

    @RequestMapping("/delCaiGouDingDan")
    public String delCaiGouDingDan(HttpSession session,@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.delUsers(list);
        StringBuffer sb = new StringBuffer();
        if(list.size() - res > 0){
            sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        }else{
            sb.append("成功删除了 ").append(res).append(" 条数据");
        }
        return ResponseCommon.success(sb.toString());
    }

    @RequestMapping("/caiGouShenHe")
    public String caiGouShenHe(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        String type = data.getString("type");
        int res = service.caiGouShenHe(list,type);
        StringBuffer sb = new StringBuffer();
        sb.append("成功审核了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条审核失败");
        return ResponseCommon.success(sb.toString());
    }


    @PostMapping("/selectXiangQingById")
    public String selectXiangQingById(HttpSession session, @RequestBody @NonNull JSONObject data) {
        Integer id = data.getInteger("id");
        String bianhao = data.getString("bianhao");
        List<CaiGouDingDan> xiaoShouDingDan = service.selectByCaiGouId(id);
        List<CaiGouDingDanXiangQing> shangPin = service.xiangQingShangPin(id,bianhao);
        List<CaiGouDingDanXiangQing> chuKu = service.xiangQingChuKu(bianhao);
        List<CaiGouDingDanXiangQing> shoukuan = service.xiangQingShouKuan(bianhao);
        List<CaiGouDingDanXiangQing> kaipiao = service.xiangQingKaiPiao(bianhao);

        JSONObject json = ResponseErrorCode.SUCCESS.toJSONObject();
        json.put("dingdan", xiaoShouDingDan);
        json.put("shangpin", shangPin);
        json.put("chuku", chuKu);
        json.put("shoukuan", shoukuan);
        json.put("kaipiao", kaipiao);
        String js = json.toJSONString();
        return json.toJSONString();
    }


    @PostMapping("/xiangQingXuKaiPiao")
    public String xiangQingXuKaiPiao(HttpSession session, @RequestBody @NonNull JSONObject data) {
        String bianhao = data.getString("bianhao");
        List<CaiGouDingDanXiangQing> xukaipiao = service.xiangQingXuKaiPiao(bianhao);
        JSONObject json = ResponseErrorCode.SUCCESS.toJSONObject();
        json.put("xukaipiao", xukaipiao);
        String js = json.toJSONString();
        return json.toJSONString();
    }


}
