package com.myboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.myboot.common.ResponseCommon;
import com.myboot.common.ResponseErrorCode;
import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.FileTable;
import com.myboot.service.CaiGouShouPiaoService;
import com.myboot.service.FileTableService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fileTable")
public class FileTableController {
    @Autowired
    private FileTableService service;

    @PostMapping("/getAll")
    public String getAll(@RequestBody @NonNull JSONObject data) {
        String id = data.getString("id");
        String type = data.getString("type");
        return ResponseCommon.success(service.getAll(id,type));
    }

    @PostMapping("/getById")
    public String queryList(@RequestBody @NonNull JSONObject data) {
        Integer id = data.getInteger("id");
        return ResponseCommon.success(service.getById(id));
    }

    @PostMapping("/fileAdd")
    public String fileAdd(@RequestBody FileTable fileTable) {
        Integer result = service.fileAdd(fileTable);
        return ResponseCommon.success(fileTable);
    }

    @RequestMapping("/deleteById")
    public String deleteById(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<Integer> list = data.getObject("list", ArrayList.class);
        int res = service.deleteById(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }

    @RequestMapping("/deleteByFileId")
    public String deleteByFileId(@RequestBody JSONObject data) {
        if(data == null || data.get("list") == null) {
            return ResponseCommon.failed(ResponseErrorCode.PARAM_ERROR);
        }
        List<String> list = data.getObject("list", ArrayList.class);
        int res = service.deleteByFileId(list);
        StringBuffer sb = new StringBuffer();
        sb.append("成功删除了 ").append(res).append(" 条数据，").append(list.size() - res).append(" 条删除失败");
        return ResponseCommon.success(sb.toString());
    }


}
