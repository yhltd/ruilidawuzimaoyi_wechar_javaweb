package com.myboot.service.impl;

import com.myboot.mapper.CustomerItemMapper;
import com.myboot.mapper.CustomerMapper;
import com.myboot.mapper.PeiZhiShuiLvMapper;
import com.myboot.pojo.Customer;
import com.myboot.pojo.PeiZhiShuiLv;
import com.myboot.service.CustomerService;
import com.myboot.service.PeiZhiShuiLvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeiZhiShuiLvImpl implements PeiZhiShuiLvService {

    @Autowired
    private PeiZhiShuiLvMapper mapper;

    @Override
    public List<PeiZhiShuiLv> getAll() {
        List<PeiZhiShuiLv> peiZhiShuiLv = mapper.getAll();
        return peiZhiShuiLv;
    }

    @Override
    public int update(PeiZhiShuiLv peiZhiShuiLv){
        int dingDan = mapper.update(peiZhiShuiLv);
        return dingDan;
    }

}
