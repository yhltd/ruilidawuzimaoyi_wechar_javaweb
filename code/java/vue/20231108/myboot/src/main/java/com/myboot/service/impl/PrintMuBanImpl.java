package com.myboot.service.impl;

import com.myboot.mapper.CaiGouDingDanItemMapper;
import com.myboot.mapper.CaiGouDingDanMapper;
import com.myboot.mapper.PrintMuBanMapper;
import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.PrintMuBan;
import com.myboot.service.CaiGouDingDanService;
import com.myboot.service.PrintMuBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrintMuBanImpl implements PrintMuBanService {

    @Autowired
    private PrintMuBanMapper mapper;

    @Override
    public List<PrintMuBan> getAll() {
        List<PrintMuBan> printMuBan = mapper.getAll();
        return printMuBan;
    }

    @Override
    public List<PrintMuBan> queryList(String name){
        List<PrintMuBan> printMuBan = mapper.queryList(name);
        return printMuBan;
    }

    @Override
    public List<PrintMuBan> getMuBanById(Integer id){
        List<PrintMuBan> printMuBan = mapper.getMuBanById(id);
        return printMuBan;
    }

    @Override
    public List<PrintMuBan> getMuBanByType(String type){
        List<PrintMuBan> printMuBan = mapper.getMuBanByType(type);
        return printMuBan;
    }

    @Override
    public int muBanAdd(PrintMuBan printMuBan){
        int dingDan = mapper.muBanAdd(printMuBan);
        return dingDan;
    }

    public int muBanUpd(PrintMuBan printMuBan){
        int dingDan = mapper.muBanUpd(printMuBan);
        return dingDan;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delById(List<Integer> list) {
        int count = 0;
        for(Integer id : list) {
            count += mapper.delById(id);
        }
        return count;
    }



}
