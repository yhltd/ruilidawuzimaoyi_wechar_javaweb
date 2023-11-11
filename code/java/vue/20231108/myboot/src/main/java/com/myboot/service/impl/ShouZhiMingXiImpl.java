package com.myboot.service.impl;

import com.myboot.mapper.ShouZhiMingXiItemMapper;
import com.myboot.mapper.ShouZhiMingXiMapper;
import com.myboot.pojo.ShouZhiMingXi;
import com.myboot.pojo.ShouZhiMingXiAdd;
import com.myboot.service.ShouZhiMingXiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShouZhiMingXiImpl implements ShouZhiMingXiService {

    @Autowired
    private ShouZhiMingXiMapper mapper;

    @Autowired
    private ShouZhiMingXiItemMapper itemMapper;


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
        List<ShouZhiMingXi> shouZhiMingXi = mapper.queryZhiChuList(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu);
        return shouZhiMingXi;
    }

    @Override
    public List<ShouZhiMingXi> queryZhiChuListByName(String start_date, String stop_date, String dianpu, String jizhangren, String jizhang_zhanghu, String yuangong_name) {
        List<ShouZhiMingXi> shouZhiMingXi = mapper.queryZhiChuListByName(start_date,stop_date,dianpu,jizhangren,jizhang_zhanghu,yuangong_name);
        return shouZhiMingXi;
    }

    @Override
    public int shouzhiAdd(ShouZhiMingXi shouZhiMingXi){
        Integer mingXi = mapper.shouzhiAdd(shouZhiMingXi);
        return mingXi;
    }

    @Override
    public int shouzhiUpd(ShouZhiMingXi shouZhiMingXi){
        Integer mingXi = mapper.shouzhiUpd(shouZhiMingXi);
        return mingXi;
    }

    @Override
    public int deleteById(Integer id){
        return mapper.deleteById(id);
    }

    @Override
    public List<ShouZhiMingXi> selectById(Integer id){
        return mapper.selectById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delUsers(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.deleteById(id);
            itemMapper.deleteByShouZhiId(id);
        }
        return count;
    }

}
