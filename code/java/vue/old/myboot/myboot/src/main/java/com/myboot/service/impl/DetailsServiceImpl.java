package com.myboot.service.impl;

import com.myboot.mapper.DetailsMapper;
import com.myboot.pojo.Details;
import com.myboot.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private DetailsMapper mapper;

    @Override
    public List<Details> getAll() {
        List<Details> details = mapper.getAll();
        return details;
    }

    @Override
    public boolean detailsAdd(Details details) {
        boolean flag = false;
        int result = mapper.detailAdd(details);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean detailsUpdate(Details details) {
        boolean flag = false;
        int res = mapper.detailsUpdate(details);
        if (res == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean detailsDel(Integer id) {
        boolean flag = false;
        int res = mapper.detailsDel(id);
        if (res == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }
}
