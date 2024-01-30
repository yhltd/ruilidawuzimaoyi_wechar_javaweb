package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.KuCun;
import com.myboot.pojo.ShouZhiTongJi;
import com.myboot.service.KuCunService;
import com.myboot.service.ShouZhiTongJiService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shouZhiTongJI")
public class ShouZhiTongJiController {
    @Autowired
    private ShouZhiTongJiService service;

    @PostMapping("/getShouZhi")
    public String getShouZhi(@RequestBody @NonNull JSONObject data) {
        String riqi = data.getString("riqi");
        String type = data.getString("type");
        String shouzhi_type = data.getString("shouzhi_type");
        String jizhang_danwei = data.getString("jizhang_danwei");
        String jizhang_zhanghu = data.getString("jizhang_zhanghu");
        String this_where = "";
        if(!jizhang_danwei.equals("")){
            this_where = this_where + " and jizhang_danwei ='" + jizhang_danwei + "' ";
        }
        if(!jizhang_zhanghu.equals("")){
            this_where = this_where + " and jizhang_zhanghu ='" + jizhang_zhanghu + "' ";
        }
        if(riqi == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<ShouZhiTongJi> shouZhiTongJis = null;
        if(shouzhi_type.equals("收入")){
            shouZhiTongJis = service.getShouRu(type,riqi,this_where);
        }else if(shouzhi_type.equals("支出")){
            shouZhiTongJis = service.getZhiChu(type,riqi,this_where);
        }

        return ResponseCommon.success(shouZhiTongJis);
    }

    @PostMapping("/getShouZhiByMonth")
    public String getShouZhiByMonth(@RequestBody @NonNull JSONObject data) {
        String riqi = data.getString("riqi");
        if(riqi == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<ShouZhiTongJi> shouZhiTongJis = service.getShouZhi(riqi);

        return ResponseCommon.success(shouZhiTongJis);
    }
}
