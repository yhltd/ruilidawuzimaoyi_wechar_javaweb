package com.myboot.service.impl;

import com.myboot.mapper.PeiZhiGuiGeMapper;
import com.myboot.pojo.PeiZhiGuiGe;
import com.myboot.service.PeiZhiGuiGeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PeiZhiGuiGeImpl implements PeiZhiGuiGeService {
    @Autowired
    private PeiZhiGuiGeMapper mapper;
    @Override
    public List<PeiZhiGuiGe> getAll() {
        List<PeiZhiGuiGe> peiZhiGuiGe = mapper.getAll();
        return peiZhiGuiGe;
    }

    @Override
    public List<PeiZhiGuiGe> selectByProduct(String type,String guige) {
        List<PeiZhiGuiGe> peiZhiGuiGe = mapper.selectByProduct(type,guige);
        return peiZhiGuiGe;
    }

    @Override
    public int peiZhiGuiGeAdd(PeiZhiGuiGe peiZhiGuiGe) {
        return mapper.peiZhiGuiGeAdd(peiZhiGuiGe);
    }

    @Override
    public int peiZhiGuiGeUpd(PeiZhiGuiGe peiZhiGuiGe) {
        return mapper.peiZhiGuiGeUpd(peiZhiGuiGe);
    }

    @Override
    public int delByPeiZhiGuiGeId(Integer id) {
        return mapper.delByPeiZhiGuiGeId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delPeiZhiGuiGe(List<Integer> list) {
        int count = 0;
        for (Integer id : list) {
            count += mapper.delByPeiZhiGuiGeId(id);
        }
        return count;
    }
}
