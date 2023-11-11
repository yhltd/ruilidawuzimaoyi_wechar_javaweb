package com.myboot.service.impl;

import com.myboot.mapper.ReceivingMapper;
import com.myboot.pojo.Receiving;
import com.myboot.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivingServiceImpl implements ReceivingService {
    @Autowired
    private ReceivingMapper mapper;

    @Override
    public List<Receiving> getAll() {
        List<Receiving> receivingList = mapper.getAll();
        return receivingList;
    }

    @Override
    public boolean receAdd(Receiving receiving) {
        boolean flag = false;
        int result = mapper.receAdd(receiving);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean receUpdate(Receiving receiving) {
        boolean flag = false;
        int result = mapper.receUpdate(receiving);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean receDel(Integer id) {
        boolean flag = false;
        int result = mapper.receDel(id);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }
}
