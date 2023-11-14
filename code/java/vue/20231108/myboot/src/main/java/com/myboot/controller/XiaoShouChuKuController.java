package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouRuKuItemService;
import com.myboot.service.CaiGouRuKuService;
import com.myboot.service.XiaoShouChuKuItemService;
import com.myboot.service.XiaoShouChuKuService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xiaoShouChuKu")
public class XiaoShouChuKuController {
    @Autowired
    private XiaoShouChuKuService service;

    @Autowired
    private XiaoShouChuKuItemService itemService;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String kehu = data.getString("kehu");
        List<XiaoShouChuKu> xiaoShouChuKu = service.queryList(start_date,stop_date,kehu);
        return ResponseCommon.success(xiaoShouChuKu);
    }

    @PostMapping("/selectMaxDanHao")
    public String selectMaxDanHao(HttpSession session, @RequestBody @NonNull JSONObject data) {
        List<XiaoShouChuKu> xiaoShouChuKu = service.selectMaxDanHao();
        return ResponseCommon.success(xiaoShouChuKu);
    }

    @PostMapping("/xiaoShouChuKuAdd")
    public String xiaoShouChuKuAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        XiaoShouChuKu xiaoShouChuKu = data.getObject("head", XiaoShouChuKu.class);
        List<XiaoShouChuKuItem> xiaoShouChuKuItems = body.toJavaList(XiaoShouChuKuItem.class);
        XiaoShouChuKu dingDan = new XiaoShouChuKu();
        BeanUtils.copyProperties(xiaoShouChuKu, dingDan);
        Integer this_id = service.chuKuAdd(dingDan);
        for(int i=0; i<xiaoShouChuKuItems.size(); i++){
            xiaoShouChuKuItems.get(i).setChukuId(dingDan.getId());
            int result2 = itemService.chuKuItemAdd(xiaoShouChuKuItems.get(i));
        }
        return ResponseCommon.success(xiaoShouChuKu);
    }

    @PostMapping("/xiaoShouChuKuUpd")
    public String xiaoShouChuKuUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        XiaoShouChuKu xiaoShouChuKu = data.getObject("head", XiaoShouChuKu.class);
        List<XiaoShouChuKuItem> xiaoShouChuKuItems = body.toJavaList(XiaoShouChuKuItem.class);
        XiaoShouChuKu dingDan = new XiaoShouChuKu();
        BeanUtils.copyProperties(xiaoShouChuKu, dingDan);
        Integer this_id = service.chuKuUpd(dingDan);
        itemService.deleteByChuKuItemId(dingDan.getId());
        for(int i=0; i<xiaoShouChuKuItems.size(); i++){
            xiaoShouChuKuItems.get(i).setChukuId(dingDan.getId());
            int result2 = itemService.chuKuItemAdd(xiaoShouChuKuItems.get(i));
        }
        return ResponseCommon.success(xiaoShouChuKu);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectByChuKuId")
    public String selectByChuKuId(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        Integer id = data.getInteger("id");
        List<XiaoShouChuKu> xiaoShouChuKu = service.selectByChuKuId(data.getInteger("id"));
        XiaoShouChuKuAdd xiaoShouChuKuAdd = new XiaoShouChuKuAdd();
        xiaoShouChuKuAdd.setId(xiaoShouChuKu.get(0).getId());
        xiaoShouChuKuAdd.setBianhao(xiaoShouChuKu.get(0).getBianhao());
        xiaoShouChuKuAdd.setRiqi(xiaoShouChuKu.get(0).getRiqi());
        xiaoShouChuKuAdd.setKehu(xiaoShouChuKu.get(0).getKehu());
        xiaoShouChuKuAdd.setDianpu(xiaoShouChuKu.get(0).getDianpu());
        xiaoShouChuKuAdd.setCangku(xiaoShouChuKu.get(0).getCangku());
        xiaoShouChuKuAdd.setBeizhu(xiaoShouChuKu.get(0).getBeizhu());
        xiaoShouChuKuAdd.setChuKuItem(itemService.selectByChuKuItemId(data.getInteger("id")));

        if (xiaoShouChuKuAdd == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(xiaoShouChuKuAdd);
    }

    @PostMapping("/selectWeiFu")
    public String selectWeiFu(HttpSession session,@RequestBody @NonNull JSONObject data) {
        List<XiaoShouChuKu> xiaoShouChuKu = service.selectWeiFu();
        return ResponseCommon.success(xiaoShouChuKu);
    }

    @RequestMapping("/delXiaoShouChuKu")
    public String delXiaoShouChuKu(@RequestBody JSONObject data) {
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
