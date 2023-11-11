package com.myboot.service;

import com.myboot.pojo.AccountingUnit;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountingUnitService {
    //查询
    public List<AccountingUnit> getAll();
    //增加
    public boolean accountAdd(String aName);
    //修改
    public boolean accountUpdate(String aName,Integer aId);
    //删除
    public boolean accountDelete(Integer aId);
}
