package com.myboot.service;

import com.myboot.pojo.JiZhang;
import com.myboot.pojo.KuCun;

import java.util.List;

public interface JiZhangService {


    //条件查询
    public List<JiZhang> getJiZhang(String start_date, String stop_date, String dianpu, String jizhang_danwei, String jizhang_zhanghu);


}