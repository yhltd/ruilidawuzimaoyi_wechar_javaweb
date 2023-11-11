package com.myboot.service;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.Peizhi;
import com.myboot.pojo.PeizhiTypeEnum;

import java.util.List;

/**
 * @author hui
 * @date 2023/11/8 11:20
 */
public interface PeizhiService {
    /**
     * 本接口可查询 peizhi 表中所有 type 分类下的所有 name
     * @param type 分类枚举类型
     * @return
     * @see PeizhiTypeEnum
     */
    List<Peizhi> queryAllByType(String type);

    //增加
    public int peiZhiAdd(Peizhi peizhi);

    //修改
    public int peiZhiUpd(Peizhi peizhi);

    //根据采购id删除
    public int deleteByPeiZhiId(Integer id);

    //根据采购id查询
    public List<Peizhi> selectByPeiZhiId(Integer id);

    int delUsers(List<Integer> list);
}
