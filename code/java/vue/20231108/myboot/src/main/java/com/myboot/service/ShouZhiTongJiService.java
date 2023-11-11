package com.myboot.service;

import com.myboot.pojo.KuCun;
import com.myboot.pojo.ShouZhiTongJi;

import java.util.List;

public interface ShouZhiTongJiService {

    //查询全部
    public List<ShouZhiTongJi> getShouRu(String type, String riqi);

    public List<ShouZhiTongJi> getZhiChu(String type, String riqi);

    public List<ShouZhiTongJi> getShouZhi(String riqi);

}