package com.myboot.service;


import com.myboot.pojo.ContactPerson;

import java.util.List;

public interface ContactPersonService {
    public List<ContactPerson> getAll();
    public boolean contactAdd(ContactPerson contactPerson);
    public boolean contactUpdate(ContactPerson contactPerson);
    public boolean contactDel(Integer id);
}
