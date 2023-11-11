package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.CaiGouRuKuItemService;
import com.myboot.service.CaiGouRuKuService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/caiGouRuKu")
public class CaiGouRuKuController {
    @Autowired
    private CaiGouRuKuService service;

    @Autowired
    private CaiGouRuKuItemService itemService;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String gongyingshang = data.getString("gongyingshang");
        List<CaiGouRuKu> caiGouRuKu = service.queryList(start_date,stop_date,gongyingshang);
        return ResponseCommon.success(caiGouRuKu);
    }


    @PostMapping("/caiGouRuKuAdd")
    public String caiGouRuKuAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        CaiGouRuKu caiGouRuKu = data.getObject("head", CaiGouRuKu.class);
        List<CaiGouRuKuItem> caiGouRuKuItems = body.toJavaList(CaiGouRuKuItem.class);
        CaiGouRuKu dingDan = new CaiGouRuKu();
        BeanUtils.copyProperties(caiGouRuKu, dingDan);
        Integer this_id = service.caiGouRuKuAdd(dingDan);
        for(int i=0; i<caiGouRuKuItems.size(); i++){
            caiGouRuKuItems.get(i).setRukuId(dingDan.getId());
            int result2 = itemService.caiGouRuKuItemAdd(caiGouRuKuItems.get(i));
        }
        return ResponseCommon.success(caiGouRuKu);
    }

    @PostMapping("/caiGouRuKuUpd")
    public String caiGouRuKuUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        CaiGouRuKu caiGouRuKu = data.getObject("head", CaiGouRuKu.class);
        List<CaiGouRuKuItem> CaiGouRuKuItems = body.toJavaList(CaiGouRuKuItem.class);
        CaiGouRuKu dingDan = new CaiGouRuKu();
        BeanUtils.copyProperties(caiGouRuKu, dingDan);
        Integer this_id = service.caiGouRuKuUpd(dingDan);
        itemService.deleteByRuKuItemId(dingDan.getId());
        for(int i=0; i<CaiGouRuKuItems.size(); i++){
            CaiGouRuKuItems.get(i).setRukuId(dingDan.getId());
            int result2 = itemService.caiGouRuKuItemAdd(CaiGouRuKuItems.get(i));
        }
        return ResponseCommon.success(caiGouRuKu);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectByRuKuId")
    public String selectByRuKuId(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        Integer id = data.getInteger("id");
        List<CaiGouRuKu> caiGouRuKu = service.selectByRuKuId(data.getInteger("id"));
        CaiGouRuKuAdd caiGouRuKuAdd = new CaiGouRuKuAdd();
        caiGouRuKuAdd.setId(caiGouRuKu.get(0).getId());
        caiGouRuKuAdd.setBianhao(caiGouRuKu.get(0).getBianhao());
        caiGouRuKuAdd.setRiqi(caiGouRuKu.get(0).getRiqi());
        caiGouRuKuAdd.setGongyingshang(caiGouRuKu.get(0).getGongyingshang());
        caiGouRuKuAdd.setDianpu(caiGouRuKu.get(0).getDianpu());
        caiGouRuKuAdd.setCangku(caiGouRuKu.get(0).getCangku());
        caiGouRuKuAdd.setBeizhu(caiGouRuKu.get(0).getBeizhu());
        caiGouRuKuAdd.setRukuItem(itemService.selectByRuKuItemId(data.getInteger("id")));

        if (caiGouRuKuAdd == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(caiGouRuKuAdd);
    }

    @RequestMapping("/delCaiGouDingDan")
    public String delCaiGouDingDan(@RequestBody JSONObject data) {
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


}
