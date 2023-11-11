package com.myboot.service;

import com.myboot.pojo.Invoicing;

import java.util.List;

public interface InvoicingService {
    public List<Invoicing> getAll();
    public boolean invoicingAdd(Invoicing invoicing);
    public boolean invoicingUpd(Invoicing invoicing);
    public boolean invoicingDel(Integer id);
}
