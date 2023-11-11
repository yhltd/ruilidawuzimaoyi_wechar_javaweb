package com.myboot.service.impl;

import com.myboot.mapper.AccountingUnitMapper;
import com.myboot.pojo.AccountingUnit;
import com.myboot.service.AccountingUnitService;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingUnitServiceImpl implements AccountingUnitService {
    @Autowired
    private AccountingUnitMapper mapper;

    @Override
    public List<AccountingUnit> getAll() {
        List<AccountingUnit> accountingUnits = mapper.getAll();
        return accountingUnits;
    }

    @Override
    public boolean accountAdd(String aName) {
        boolean flag = false;
        int result = mapper.accountAdd(aName);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean accountUpdate(String aName, Integer aId) {
        boolean flag = false;
        int result = mapper.accountUpdate(aName,aId);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean accountDelete(Integer aId) {
        boolean flag = false;
        int result = mapper.accountDelete(aId);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }
}
