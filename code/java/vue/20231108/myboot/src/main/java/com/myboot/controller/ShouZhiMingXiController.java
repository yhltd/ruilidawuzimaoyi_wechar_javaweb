package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.ShouZhiMingXiItemService;
import com.myboot.service.ShouZhiMingXiService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shouZhiMingXi")
public class ShouZhiMingXiController {
    @Autowired
    private ShouZhiMingXiService service;

    @Autowired
    private ShouZhiMingXiItemService itemService;

    @GetMapping("/getShouRu")
    public String getShouRu() {
        return ResponseCommon.success(service.getShouRu());
    }

    @GetMapping("/getZhiChu")
    public String getZhiChu() {
        return ResponseCommon.success(service.getZhiChu());
    }

    @PostMapping("/getShouRuByName")
    public String getShouRuByName(@RequestBody @NonNull JSONObject data) {
        String yuangong_name = data.getString("yuangong_name");
        return ResponseCommon.success(service.getShouRuByName(yuangong_name));
    }

    @PostMapping("/getZhiChuByName")
    public String getZhiChuByName(@RequestBody @NonNull JSONObject data) {
        String yuangong_name = data.getString("yuangong_name");
        return ResponseCommon.success(service.getZhiChuByName(yuangong_name));
    }

    @PostMapping("/queryShouRuList")
    public String queryShouRuList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String dianpu = data.getString("dianpu");
        String jizhangren = data.getString("jizhangren");
        String jizhang_zhanghu = data.getString("jizhang_zhanghu");
        List<ShouZhiMingXi> shouZhiMingXi = service.queryShouRuList(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu);
        return ResponseCommon.success(shouZhiMingXi);
    }

    @PostMapping("/queryShouRuListByName")
    public String queryShouRuListByName(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String dianpu = data.getString("dianpu");
        String jizhangren = data.getString("jizhangren");
        String jizhang_zhanghu = data.getString("jizhang_zhanghu");
        String yuangong_name = data.getString("yuangong_name");
        List<ShouZhiMingXi> shouZhiMingXi = service.queryShouRuListByName(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu,yuangong_name);
        return ResponseCommon.success(shouZhiMingXi);
    }

    @PostMapping("/queryZhiChuList")
    public String queryZhiChuList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String dianpu = data.getString("dianpu");
        String jizhangren = data.getString("jizhangren");
        String jizhang_zhanghu = data.getString("jizhang_zhanghu");
        List<ShouZhiMingXi> shouZhiMingXi = service.queryZhiChuList(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu);
        return ResponseCommon.success(shouZhiMingXi);
    }

    @PostMapping("/queryZhiChuListByName")
    public String queryZhiChuListByName(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String dianpu = data.getString("dianpu");
        String jizhangren = data.getString("jizhangren");
        String jizhang_zhanghu = data.getString("jizhang_zhanghu");
        String yuangong_name = data.getString("yuangong_name");
        List<ShouZhiMingXi> shouZhiMingXi = service.queryZhiChuListByName(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu,yuangong_name);
        return ResponseCommon.success(shouZhiMingXi);
    }

    @PostMapping("/shouzhiAdd")
    public String shouzhiAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        ShouZhiMingXi shouZhiMingXi = data.getObject("head", ShouZhiMingXi.class);
        List<ShouZhiMingXiItem> shouZhiMingXiItems = body.toJavaList(ShouZhiMingXiItem.class);
        ShouZhiMingXi dingDan = new ShouZhiMingXi();
        BeanUtils.copyProperties(shouZhiMingXi, dingDan);
        Integer this_id = service.shouzhiAdd(dingDan);
        for(int i=0; i<shouZhiMingXiItems.size(); i++){
            shouZhiMingXiItems.get(i).setShouzhiId(String.valueOf(dingDan.getId()));
            int result2 = itemService.shouZhiItemAdd(shouZhiMingXiItems.get(i));
        }
        return ResponseCommon.success(dingDan);
    }

    @PostMapping("/shouzhiUpd")
    public String shouzhiUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        ShouZhiMingXi shouZhiMingXi = data.getObject("head", ShouZhiMingXi.class);
        List<ShouZhiMingXiItem> shouZhiMingXiItems = body.toJavaList(ShouZhiMingXiItem.class);
        ShouZhiMingXi dingDan = new ShouZhiMingXi();
        BeanUtils.copyProperties(shouZhiMingXi, dingDan);
        Integer this_id = service.shouzhiUpd(dingDan);
        itemService.deleteByShouZhiId(dingDan.getId());
        for(int i=0; i<shouZhiMingXiItems.size(); i++){
            shouZhiMingXiItems.get(i).setShouzhiId(String.valueOf(dingDan.getId()));
            int result2 = itemService.shouZhiItemAdd(shouZhiMingXiItems.get(i));
        }
        return ResponseCommon.success(shouZhiMingXi);
    }

    @PostMapping("/selectMaxDanHao")
    public String selectMaxDanHao(@RequestBody @NonNull JSONObject data) {
        List<ShouZhiMingXi> shouZhiMingXi = service.selectMaxDanHao();
        return ResponseCommon.success(shouZhiMingXi);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectById")
    public String selectById(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<ShouZhiMingXi> shouZhiMingXi = service.selectById(data.getInteger("id"));
        ShouZhiMingXiAdd shouZhiMingXiAdd = new ShouZhiMingXiAdd();
        shouZhiMingXiAdd.setId(shouZhiMingXi.get(0).getId());
        shouZhiMingXiAdd.setDianpu(shouZhiMingXi.get(0).getDianpu());
        shouZhiMingXiAdd.setDanjuLeixing(shouZhiMingXi.get(0).getDanjuLeixing());
        shouZhiMingXiAdd.setDanjuBianhao(shouZhiMingXi.get(0).getDanjuBianhao());
        shouZhiMingXiAdd.setShouzhiBianhao(shouZhiMingXi.get(0).getShouzhiBianhao());
        shouZhiMingXiAdd.setShouzhiRiqi(shouZhiMingXi.get(0).getShouzhiRiqi());
        shouZhiMingXiAdd.setJizhangren(shouZhiMingXi.get(0).getJizhangren());
        shouZhiMingXiAdd.setJizhangType(shouZhiMingXi.get(0).getJizhangType());
        shouZhiMingXiAdd.setJizhangZhanghu(shouZhiMingXi.get(0).getJizhangZhanghu());
        shouZhiMingXiAdd.setJizhangJine(shouZhiMingXi.get(0).getJizhangJine());
        shouZhiMingXiAdd.setKediShuie(shouZhiMingXi.get(0).getKediShuie());
        shouZhiMingXiAdd.setZhaiyao(shouZhiMingXi.get(0).getZhaiyao());
        shouZhiMingXiAdd.setShouzhiType(shouZhiMingXi.get(0).getShouzhiType());
        shouZhiMingXiAdd.setShouzhiItem(itemService.selectByShouZhiId(data.getInteger("id")));

        if (shouZhiMingXi == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(shouZhiMingXiAdd);
    }

    @RequestMapping("/delShouZhi")
    public String delShouZhi(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.delUsers(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }


}
