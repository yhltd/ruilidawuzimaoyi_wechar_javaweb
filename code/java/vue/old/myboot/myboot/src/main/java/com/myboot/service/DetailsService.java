package com.myboot.service;

import com.myboot.pojo.Details;

import java.util.List;

public interface DetailsService {
    public List<Details> getAll();
    public boolean detailsAdd(Details details);
    public boolean detailsUpdate(Details details);
    public boolean detailsDel(Integer id);
}
