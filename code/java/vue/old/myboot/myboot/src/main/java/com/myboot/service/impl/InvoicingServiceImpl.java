package com.myboot.service.impl;

import com.myboot.mapper.InvoicingMapper;
import com.myboot.pojo.Invoicing;
import com.myboot.service.InvoicingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoicingServiceImpl implements InvoicingService {
    @Autowired
    private InvoicingMapper mapper;

    @Override
    public List<Invoicing> getAll() {
        return mapper.getAll();
    }

    @Override
    public boolean invoicingAdd(Invoicing invoicing) {
        boolean flag = false;
        int res = mapper.invoicingAdd(invoicing);
        if (res == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean invoicingUpd(Invoicing invoicing) {
        boolean flag = false;
        int res = mapper.invoicingUpd(invoicing);
        if (res == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean invoicingDel(Integer id) {
        boolean flag = false;
        int res = mapper.invoicingDel(id);
        if (res == 1){
            return flag;
        }
        else {
            return flag;
        }
    }
}
