package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.mapper.ShouZhiMingXiMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.ShouZhiMingXi;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.ShouZhiMingXiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShouZhiMingXiImpl implements ShouZhiMingXiService {

    @Autowired
    private ShouZhiMingXiMapper mapper;

    @Override
    public List<ShouZhiMingXi> getShouRu() {
        List<ShouZhiMingXi> shouZhiMingXi = mapper.getShouRu();
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> getShouRuByName(String yuangong_name) {
        List<ShouZhiMingXi> shouZhiMingXi = mapper.getShouRuByName(yuangong_name);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> getZhiChu() {
        List<ShouZhiMingXi> shouZhiMingXi = mapper.getZhiChu();
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> getZhiChuByName(String yuangong_name) {
        List<ShouZhiMingXi> shouZhiMingXi = mapper.getZhiChuByName(yuangong_name);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> queryShouRuList(String start_date,String stop_date,String dianpu, String jizhangren, String jizhang_zhanghu){
        List<ShouZhiMingXi> shouZhiMingXi = mapper.queryShouRuList(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> queryShouRuListByName(String start_date, String stop_date, String dianpu, String jizhangren, String jizhang_zhanghu, String yuangong_name) {
        List<ShouZhiMingXi> shouZhiMingXi = mapper.queryShouRuListByName(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu,yuangong_name);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> queryZhiChuList(String start_date,String stop_date,String dianpu, String jizhangren, String jizhang_zhanghu){
        List<ShouZhiMingXi> shouZhiMingXi = mapper.queryShouRuList(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> queryZhiChuListByName(String start_date, String stop_date, String dianpu, String jizhangren, String jizhang_zhanghu, String yuangong_name) {
        List<ShouZhiMingXi> shouZhiMingXi = mapper.queryZhiChuListByName(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu,yuangong_name);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> shouzhiAdd(String dianpu,String danju_leixing,String danju_bianhao, String shouzhi_bianhao, String shouzhi_riqi,String jizhangren,String jizhang_type, String jizhang_zhanghu, String jizhang_jine, String kedi_shuie, String zhaiyao, String shouzhi_type){
        List<ShouZhiMingXi> shouZhiMingXi = mapper.shouzhiAdd(dianpu,danju_leixing,danju_bianhao,shouzhi_bianhao,shouzhi_riqi,jizhangren,jizhang_type,jizhang_zhanghu,jizhang_jine,kedi_shuie,zhaiyao,shouzhi_type);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> shouzhiUpd(String dianpu,String danju_leixing,String danju_bianhao, String shouzhi_bianhao, String shouzhi_riqi,String jizhangren,String jizhang_type, String jizhang_zhanghu, String jizhang_jine, String kedi_shuie, String zhaiyao, String shouzhi_type,Integer id){
        List<ShouZhiMingXi> shouZhiMingXi = mapper.shouzhiUpd(dianpu,danju_leixing,danju_bianhao,shouzhi_bianhao,shouzhi_riqi,jizhangren,jizhang_type,jizhang_zhanghu,jizhang_jine,kedi_shuie,zhaiyao,shouzhi_type,id);
        return shouZhiMingXi;
    }

    @Override
    public boolean deleteById(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public List<ShouZhiMingXi> selectById(Integer id){
        return mapper.selectById(id);
    }


}
