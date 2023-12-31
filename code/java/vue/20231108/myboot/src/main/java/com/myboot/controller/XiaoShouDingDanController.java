package com.myboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.*;
import com.myboot.service.XiaoShouBaoJiaItemService;
import com.myboot.service.XiaoShouBaoJiaService;
import com.myboot.service.XiaoShouDingDanItemService;
import com.myboot.service.XiaoShouDingDanService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xiaoShouDingDan")
public class XiaoShouDingDanController {
    @Autowired
    private XiaoShouDingDanService service;

    @Autowired
    private XiaoShouDingDanItemService itemService;

    @GetMapping("/getAll")
    public String getAll() {
        return ResponseCommon.success(service.getAll());
    }

    @PostMapping("/getAllByName")
    public String getAllByName(@RequestBody @NonNull JSONObject data) {
        String yewuyuan = data.getString("yewuyuan");
        return ResponseCommon.success(service.getAllByName(yewuyuan));
    }

    @PostMapping("/queryList")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String kehu = data.getString("kehu");
        String shenhe_zhuangtai = data.getString("shenhe_zhuangtai");
        List<XiaoShouDingDan> xiaoShouDingDan = service.queryList(start_date,stop_date,kehu,shenhe_zhuangtai);
        return ResponseCommon.success(xiaoShouDingDan);
    }

    @PostMapping("/queryListByName")
    public String queryListByName(@RequestBody @NonNull JSONObject data) {
        String start_date = data.getString("start_date");
        String stop_date = data.getString("stop_date");
        String kehu = data.getString("kehu");
        String shenhe_zhuangtai = data.getString("shenhe_zhuangtai");
        String yewuyuan = data.getString("yewuyuan");
        List<XiaoShouDingDan> xiaoShouDingDan = service.queryListByName(yewuyuan,start_date,stop_date,kehu,shenhe_zhuangtai);
        return ResponseCommon.success(xiaoShouDingDan);
    }

    @PostMapping("/shenheList")
    public String shenheList(@RequestBody @NonNull JSONObject data) {
        String name = data.getString("name");
        List<XiaoShouDingDan> xiaoShouDingDan = service.shenHeList(name);
        return ResponseCommon.success(xiaoShouDingDan);
    }

    @PostMapping("/xiaoShouAdd")
    public String xiaoShouAdd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        XiaoShouDingDan xiaoShouDingDan = data.getObject("head", XiaoShouDingDan.class);
        List<XiaoShouDingDanItem> xiaoShouDingDanItems = body.toJavaList(XiaoShouDingDanItem.class);
        XiaoShouDingDan dingDan = new XiaoShouDingDan();
        BeanUtils.copyProperties(xiaoShouDingDan, dingDan);
        Integer this_id = service.xiaoShouAdd(dingDan);
        for(int i=0; i<xiaoShouDingDanItems.size(); i++){
            xiaoShouDingDanItems.get(i).setXiaoshouId(String.valueOf(dingDan.getId()));
            int result2 = itemService.xiaoShouItemAdd(xiaoShouDingDanItems.get(i));
        }
        return ResponseCommon.success(xiaoShouDingDan);
    }

    @PostMapping("/xiaoShouUpd")
    public String xiaoShouUpd(@RequestBody @NonNull JSONObject data) {
        System.out.println(data);
        JSONObject head = data.getJSONObject("head");
        JSONArray body = data.getJSONArray("body");
        XiaoShouDingDan xiaoShouDingDan = data.getObject("head", XiaoShouDingDan.class);
        List<XiaoShouDingDanItem> xiaoShouDingDanItems = body.toJavaList(XiaoShouDingDanItem.class);
        XiaoShouDingDan dingDan = new XiaoShouDingDan();
        BeanUtils.copyProperties(xiaoShouDingDan, dingDan);
        Integer this_id = service.xiaoShouUpd(dingDan);
        itemService.deleteByXiaoShouId(dingDan.getId());
        for(int i=0; i<xiaoShouDingDanItems.size(); i++){
            xiaoShouDingDanItems.get(i).setXiaoshouId(String.valueOf(dingDan.getId()));
            int result2 = itemService.xiaoShouItemAdd(xiaoShouDingDanItems.get(i));
        }
        return ResponseCommon.success(xiaoShouDingDan);
    }

    /**
     * @param data
     * @return
     */
    @PostMapping("/selectXiaoShouById")
    public String selectXiaoShouById(@RequestBody JSONObject data) {
        if (data == null || data.getInteger("id") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<XiaoShouDingDan> xiaoShouDingDan = service.selectXiaoShouById(data.getInteger("id"));
        XiaoShouDingDanAdd xiaoShouDingDanAdd = new XiaoShouDingDanAdd();
        xiaoShouDingDanAdd.setId(xiaoShouDingDan.get(0).getId());
        xiaoShouDingDanAdd.setBianhao(xiaoShouDingDan.get(0).getBianhao());
        xiaoShouDingDanAdd.setRiqi(xiaoShouDingDan.get(0).getRiqi());
        xiaoShouDingDanAdd.setKehu(xiaoShouDingDan.get(0).getKehu());
        xiaoShouDingDanAdd.setYewuyuan(xiaoShouDingDan.get(0).getYewuyuan());
        xiaoShouDingDanAdd.setShoujianren(xiaoShouDingDan.get(0).getShoujianren());
        xiaoShouDingDanAdd.setShoujianPhone(xiaoShouDingDan.get(0).getShoujianPhone());
        xiaoShouDingDanAdd.setShoujianDizhi(xiaoShouDingDan.get(0).getShoujianDizhi());
        xiaoShouDingDanAdd.setDianpu(xiaoShouDingDan.get(0).getDianpu());
        xiaoShouDingDanAdd.setXiaoxiangShuilv(xiaoShouDingDan.get(0).getXiaoxiangShuilv());
        xiaoShouDingDanAdd.setBeizhu(xiaoShouDingDan.get(0).getBeizhu());
        xiaoShouDingDanAdd.setShenhe(xiaoShouDingDan.get(0).getShenhe());
        xiaoShouDingDanAdd.setShenheZhuangtai(xiaoShouDingDan.get(0).getShenheZhuangtai());
        xiaoShouDingDanAdd.setBeizhu(xiaoShouDingDan.get(0).getBeizhu());
        xiaoShouDingDanAdd.setJiashuiHeji(xiaoShouDingDan.get(0).getJiashuiHeji());
        xiaoShouDingDanAdd.setJiageDengji(xiaoShouDingDan.get(0).getJiageDengji());
        xiaoShouDingDanAdd.setShenheList(xiaoShouDingDan.get(0).getShenheList());
        xiaoShouDingDanAdd.setXiaoshouDanwei(xiaoShouDingDan.get(0).getXiaoshouDanwei());
        xiaoShouDingDanAdd.setItemList(itemService.selectByXiaoShouId(data.getInteger("id")));

        if (xiaoShouDingDan == null) {
            return ResponseCommon.failed(ResponseErrorCode.SEL_FAILED, "未找到该订单");
        }
        return ResponseCommon.success(xiaoShouDingDanAdd);
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
    //ztt 审核
    @RequestMapping("/dingDanShenHe")
    public String dingDanShenHe(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        JSONArray json = data.getJSONArray("list");
//        List<Integer> list = data.getObject("list", ArrayList.class);
        List<XiaoShouDingDan> list = JSONArray.parseArray(json.toJSONString(), XiaoShouDingDan.class);
        String type = data.getString("type");
        int res = service.xiaoShouShenHe(list ,type);
        StringBuffer sb = new StringBuffer();
        sb.append("成功审核了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条审核失败");
        return ResponseCommon.success(sb.toString());
    }
    @GetMapping("/getAllYiShen")
    public String getAllYiShen() {
        return ResponseCommon.success(service.getAllYiShen());
    }
//ztt end

    @PostMapping("/selectWeiFu")
    public String selectWeiFu(HttpSession session, @RequestBody @NonNull JSONObject data) {
        List<XiaoShouDingDan> xiaoShouDingDan = service.selectWeiFu();
        return ResponseCommon.success(xiaoShouDingDan);
    }

    @PostMapping("/selectMaxDanHao")
    public String selectMaxDanHao(HttpSession session, @RequestBody @NonNull JSONObject data) {
        List<XiaoShouDingDan> xiaoShouDingDan = service.selectMaxDanHao();
        return ResponseCommon.success(xiaoShouDingDan);
    }


    @PostMapping("/selectXiangQingById")
    public String selectXiangQingById(HttpSession session, @RequestBody @NonNull JSONObject data) {
        Integer id = data.getInteger("id");
        String bianhao = data.getString("bianhao");
        List<XiaoShouDingDan> xiaoShouDingDan = service.selectXiaoShouById(id);
        List<XiaoShouDingDanXiangQing> shangPin = service.xiangQingShangPin(id,bianhao);
        List<XiaoShouDingDanXiangQing> chuKu = service.xiangQingChuKu(bianhao);
        List<XiaoShouDingDanXiangQing> shoukuan = service.xiangQingShouKuan(bianhao);
        List<XiaoShouDingDanXiangQing> kaipiao = service.xiangQingKaiPiao(bianhao);
        List<XiaoShouDingDanXiangQing> caigou = service.xiangQingCaiGou(bianhao);

        JSONObject json = ResponseErrorCode.SUCCESS.toJSONObject();
        json.put("dingdan", xiaoShouDingDan);
        json.put("shangpin", shangPin);
        json.put("chuku", chuKu);
        json.put("shoukuan", shoukuan);
        json.put("kaipiao", kaipiao);
        json.put("caigou", caigou);
        String js = json.toJSONString();
        return json.toJSONString();
    }

    @PostMapping("/xiangQingXuKaiPiao")
    public String xiangQingXuKaiPiao(HttpSession session, @RequestBody @NonNull JSONObject data) {
        String bianhao = data.getString("bianhao");
        List<XiaoShouDingDanXiangQing> xukaipiao = service.xiangQingXuKaiPiao(bianhao);
        JSONObject json = ResponseErrorCode.SUCCESS.toJSONObject();
        json.put("xukaipiao", xukaipiao);
        String js = json.toJSONString();
        return json.toJSONString();
    }

}
