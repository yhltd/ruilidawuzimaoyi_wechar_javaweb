package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.ShouZhiMingXi;

import java.util.List;

public interface ShouZhiMingXiService {

    //查询全部收入
    public List<ShouZhiMingXi> getShouRu();

    //查询个人收入
    public List<ShouZhiMingXi> getShouRuByName(String yuangong_name);

    //查询全部支出
    public List<ShouZhiMingXi> getZhiChu();

    //查询个人支出
    public List<ShouZhiMingXi> getZhiChuByName(String yuangong_name);

    //条件查询全部收入
    public List<ShouZhiMingXi> queryShouRuList(String start_date,String stop_date,String dianpu, String jizhangren, String jizhang_zhanghu);

    //条件查询个人收入
    public List<ShouZhiMingXi> queryShouRuListByName(String start_date,String stop_date,String dianpu, String jizhangren, String jizhang_zhanghu, String yuangong_name);

    //条件查询支出
    public List<ShouZhiMingXi> queryZhiChuList(String start_date,String stop_date,String dianpu, String jizhangren, String jizhang_zhanghu);

    //条件查询个人支出
    public List<ShouZhiMingXi> queryZhiChuListByName(String start_date,String stop_date,String dianpu, String jizhangren, String jizhang_zhanghu, String yuangong_name);

    //收支增加
    public List<ShouZhiMingXi> shouzhiAdd(String dianpu,String danju_leixing,String danju_bianhao, String shouzhi_bianhao, String shouzhi_riqi,String jizhangren,String jizhang_type, String jizhang_zhanghu, String jizhang_jine, String kedi_shuie, String zhaiyao, String shouzhi_type);

    //收支修改
    public List<ShouZhiMingXi> shouzhiUpd(String dianpu,String danju_leixing,String danju_bianhao, String shouzhi_bianhao, String shouzhi_riqi,String jizhangren,String jizhang_type, String jizhang_zhanghu, String jizhang_jine, String kedi_shuie, String zhaiyao, String shouzhi_type,Integer id);

    //根据id删除
    public boolean deleteById(Integer id);

    //根据id查询
    public List<ShouZhiMingXi> selectById(Integer id);

}