package com.myboot.mapper;

import com.myboot.pojo.ContactPerson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContactPersonMapper {
    public List<ContactPerson> getAll();
    public int contactAdd(ContactPerson contactPerson);
    public int contactUpdate(ContactPerson contactPerson);
    public int contactDel(@Param("cpId") Integer id);
}
