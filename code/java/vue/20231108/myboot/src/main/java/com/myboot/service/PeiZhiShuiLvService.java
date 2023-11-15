package com.myboot.service;

import com.myboot.pojo.Customer;
import com.myboot.pojo.PeiZhiShuiLv;
import com.myboot.pojo.Peizhi;
import com.myboot.pojo.PeizhiTypeEnum;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/8 11:20
 */
public interface PeiZhiShuiLvService {

    //查询全部
    public List<PeiZhiShuiLv> getAll();

    //修改
    public int update(PeiZhiShuiLv peiZhiShuiLv);

}
