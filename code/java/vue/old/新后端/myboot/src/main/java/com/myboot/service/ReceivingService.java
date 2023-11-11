package com.myboot.service;

import com.myboot.pojo.Receiving;

import java.util.List;

public interface ReceivingService {
    public List<Receiving> getAll();
    public boolean receAdd(Receiving receiving);
    public boolean receUpdate(Receiving receiving);
    public boolean receDel(Integer id);
}
