package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CustomerItemService;
import com.myboot.service.CustomerService;
import com.myboot.service.GongYingShangItemService;
import com.myboot.service.GongYingShangService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gongYingShang")
public class GongYingShangController {
    @Autowired
    private GongYingShangService service;

    @Autowired
    private GongYingShangItemService itemService;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String bianhao = data.getString("bianhao");
        String name = data.getString("name");
        List<GongYingShang> gongYingShang = service.queryList(bianhao,name);
        return ResponseCommon.success(gongYingShang);
    }

    @PostMapping("/gongyingshangAdd")
    public String gongyingshangAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        GongYingShang gongYingShang = data.getObject("head", GongYingShang.class);
        List<GongYingShangItem> gongYingShangItems = body.toJavaList(GongYingShangItem.class);
        GongYingShang dingDan = new GongYingShang();
        BeanUtils.copyProperties(gongYingShang, dingDan);
        Integer this_id = service.gongyingshangAdd(dingDan);
        for(int i=0; i<gongYingShangItems.size(); i++){
            gongYingShangItems.get(i).setGongyingshangId(String.valueOf(dingDan.getId()));
            int result2 = itemService.itemAdd(gongYingShangItems.get(i));
        }
        return ResponseCommon.success(gongYingShang);
    }

    @PostMapping("/gongyingshangUpd")
    public String gongyingshangUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        GongYingShang gongYingShang = data.getObject("head", GongYingShang.class);
        List<GongYingShangItem> gongYingShangItems = body.toJavaList(GongYingShangItem.class);
        GongYingShang dingDan = new GongYingShang();
        BeanUtils.copyProperties(gongYingShang, dingDan);
        Integer this_id = service.gongyingshangUpd(dingDan);
        itemService.itemDelById(dingDan.getId());
        for(int i=0; i<gongYingShangItems.size(); i++){
            gongYingShangItems.get(i).setGongyingshangId(String.valueOf(dingDan.getId()));
            int result2 = itemService.itemAdd(gongYingShangItems.get(i));
        }
        return ResponseCommon.success(gongYingShang);
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
        Integer id = data.getInteger("id");
        List<GongYingShang> gongYingShangs = service.selectById(data.getInteger("id"));
        GongYingShangAdd gongYingShangAdd = new GongYingShangAdd();
        gongYingShangAdd.setId(gongYingShangs.get(0).getId());
        gongYingShangAdd.setBianhao(gongYingShangs.get(0).getBianhao());
        gongYingShangAdd.setName(gongYingShangs.get(0).getName());
        gongYingShangAdd.setGongyingshangDengji(gongYingShangs.get(0).getGongyingshangDengji());
        gongYingShangAdd.setSuozaiDiqu(gongYingShangs.get(0).getSuozaiDiqu());
        gongYingShangAdd.setDizhi(gongYingShangs.get(0).getDizhi());
        gongYingShangAdd.setBeizhu(gongYingShangs.get(0).getBeizhu());
        gongYingShangAdd.setCaigouyuan(gongYingShangs.get(0).getCaigouyuan());
        gongYingShangAdd.setJianzuoKehu(gongYingShangs.get(0).getJianzuoKehu());
        gongYingShangAdd.setZhuyaoYewu(gongYingShangs.get(0).getZhuyaoYewu());
        gongYingShangAdd.setShoujianName(gongYingShangs.get(0).getShoujianName());
        gongYingShangAdd.setShoujianPhone(gongYingShangs.get(0).getShoujianPhone());
        gongYingShangAdd.setShoujianDizhi(gongYingShangs.get(0).getShoujianDizhi());
        gongYingShangAdd.setKaipiaoDanwei(gongYingShangs.get(0).getKaipiaoDanwei());
        gongYingShangAdd.setShibiehao(gongYingShangs.get(0).getShibiehao());
        gongYingShangAdd.setKaipiaoDizhi(gongYingShangs.get(0).getKaipiaoDizhi());
        gongYingShangAdd.setKaipiaoDianhua(gongYingShangs.get(0).getKaipiaoDianhua());
        gongYingShangAdd.setKaipiaoYinhang(gongYingShangs.get(0).getKaipiaoYinhang());
        gongYingShangAdd.setKaipiaoZhanghao(gongYingShangs.get(0).getKaipiaoZhanghao());
        gongYingShangAdd.setItemList(itemService.selectByGongYingShangId(data.getInteger("id")));

        if (gongYingShangs == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(gongYingShangAdd);
    }

    @RequestMapping("/delGongYingShang")
    public String delGongYingShang(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.deleteById(list);
        StringBuffer sb = new StringBuffer();
        if(list.size() - res > 0){
            sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        }else{
            sb.append("成功删除了 ").append(res).append(" 条数据");
        }
        return ResponseCommon.success(sb.toString());
    }


}
