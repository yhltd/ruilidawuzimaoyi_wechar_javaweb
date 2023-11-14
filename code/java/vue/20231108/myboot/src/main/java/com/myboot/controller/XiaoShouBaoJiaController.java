package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.CaiGouDingDanItemService;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.XiaoShouBaoJiaItemService;
import com.myboot.service.XiaoShouBaoJiaService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xiaoShouBaoJia")
public class XiaoShouBaoJiaController {
    @Autowired
    private XiaoShouBaoJiaService service;

    @Autowired
    private XiaoShouBaoJiaItemService itemService;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String kehu = data.getString("kehu");
        String shenhe_zhuangtai = data.getString("shenhe_zhuangtai");
        List<XiaoShouBaoJia> xiaoShouBaoJia = service.queryList(start_date,stop_date,kehu,shenhe_zhuangtai);
        return ResponseCommon.success(xiaoShouBaoJia);
    }

    @PostMapping("/baoJiaAdd")
    public String baoJiaAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        XiaoShouBaoJia xiaoShouBaoJia = data.getObject("head", XiaoShouBaoJia.class);
        List<XiaoShouBaoJiaItem> xiaoShouBaoJiaItems = body.toJavaList(XiaoShouBaoJiaItem.class);
        XiaoShouBaoJia dingDan = new XiaoShouBaoJia();
        BeanUtils.copyProperties(xiaoShouBaoJia, dingDan);
        Integer this_id = service.baoJiaAdd(dingDan);
        for(int i=0; i<xiaoShouBaoJiaItems.size(); i++){
            xiaoShouBaoJiaItems.get(i).setBaojiaId(dingDan.getId());
            int result2 = itemService.baoJiaItemAdd(xiaoShouBaoJiaItems.get(i));
        }
        return ResponseCommon.success(xiaoShouBaoJia);
    }

    @PostMapping("/baoJiaUpd")
    public String baoJiaUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        XiaoShouBaoJia xiaoShouBaoJia = data.getObject("head", XiaoShouBaoJia.class);
        List<XiaoShouBaoJiaItem> xiaoShouBaoJiaItems = body.toJavaList(XiaoShouBaoJiaItem.class);
        XiaoShouBaoJia dingDan = new XiaoShouBaoJia();
        BeanUtils.copyProperties(xiaoShouBaoJia, dingDan);
        Integer this_id = service.baoJiaUpd(dingDan);
        itemService.deleteByBaoJiaId(dingDan.getId());
        for(int i=0; i<xiaoShouBaoJiaItems.size(); i++){
            xiaoShouBaoJiaItems.get(i).setBaojiaId(dingDan.getId());
            int result2 = itemService.baoJiaItemAdd(xiaoShouBaoJiaItems.get(i));
        }
        return ResponseCommon.success(xiaoShouBaoJia);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectBaoJiaById")
    public String selectBaoJiaById(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<XiaoShouBaoJia> xiaoShouBaoJia = service.selectBaoJiaById(data.getInteger("id"));
        XiaoShouBaoJiaAdd xiaoShouBaoJiaAdd = new XiaoShouBaoJiaAdd();
        xiaoShouBaoJiaAdd.setId(xiaoShouBaoJia.get(0).getId());
        xiaoShouBaoJiaAdd.setBianhao(xiaoShouBaoJia.get(0).getBianhao());
        xiaoShouBaoJiaAdd.setRiqi(xiaoShouBaoJia.get(0).getRiqi());
        xiaoShouBaoJiaAdd.setKehu(xiaoShouBaoJia.get(0).getKehu());
        xiaoShouBaoJiaAdd.setYewuyuan(xiaoShouBaoJia.get(0).getYewuyuan());
        xiaoShouBaoJiaAdd.setDianpu(xiaoShouBaoJia.get(0).getDianpu());
        xiaoShouBaoJiaAdd.setXiaoxiangShuilv(xiaoShouBaoJia.get(0).getXiaoxiangShuilv());
        xiaoShouBaoJiaAdd.setBeizhu(xiaoShouBaoJia.get(0).getBeizhu());
        xiaoShouBaoJiaAdd.setShenhe(xiaoShouBaoJia.get(0).getShenhe());
        xiaoShouBaoJiaAdd.setJiageDengji(xiaoShouBaoJia.get(0).getJiageDengji());
        xiaoShouBaoJiaAdd.setShenheZhuangtai(xiaoShouBaoJia.get(0).getShenheZhuangtai());
        xiaoShouBaoJiaAdd.setItemList(itemService.selectByBaoJiaId(data.getInteger("id")));

        if (xiaoShouBaoJia == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(xiaoShouBaoJiaAdd);
    }

    @RequestMapping("/delXiaoShouBaoJia")
    public String delXiaoShouBaoJia(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.delUsers(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }

    @PostMapping("/selectMaxDanHao")
    public String selectMaxDanHao(HttpSession session, @RequestBody @NonNull JSONObject data) {
        List<XiaoShouBaoJia> xiaoShouBaoJia = service.selectMaxDanHao();
        return ResponseCommon.success(xiaoShouBaoJia);
    }
}
