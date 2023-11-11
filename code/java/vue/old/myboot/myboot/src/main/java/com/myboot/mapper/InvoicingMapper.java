package com.myboot.mapper;

import com.myboot.pojo.Invoicing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvoicingMapper {
    public List<Invoicing> getAll();
    public int invoicingAdd(Invoicing invoicing);
    public int invoicingUpd(Invoicing invoicing);
    public int invoicingDel(@Param("iId") Integer id);
}
