package com.myboot.service.impl;

import com.myboot.mapper.ContactPersonMapper;
import com.myboot.pojo.ContactPerson;
import com.myboot.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {
    @Autowired
    private ContactPersonMapper mapper;

    @Override
    public List<ContactPerson> getAll() {
        List<ContactPerson> contactPeople = mapper.getAll();
        return contactPeople;
    }

    @Override
    public boolean contactAdd(ContactPerson contactPerson) {
        boolean flag = false;
        int result = mapper.contactAdd(contactPerson);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean contactUpdate(ContactPerson contactPerson) {
        boolean flag = false;
        int result = mapper.contactUpdate(contactPerson);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }

    @Override
    public boolean contactDel(Integer id) {
        boolean flag = false;
        int result = mapper.contactDel(id);
        if (result == 1){
            flag = true;
            return flag;
        }
        else {
            return flag;
        }
    }
}
