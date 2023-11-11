package com.myboot.service;

import com.myboot.pojo.XiaoShouKaiPiao;
import com.myboot.pojo.ZhuanZhang;

import java.util.List;

public interface ZhuanZhangService {

    //查询全部
    public List<ZhuanZhang> getAll();

    //条件查询
    public List<ZhuanZhang> queryList(String start_date,String stop_date,String zhuanru, String zhuanchu);

    //增加
    public int zhuanZhangAdd(ZhuanZhang zhuanZhang);

    //修改
    public int zhuanZhangUpd(ZhuanZhang zhuanZhang);

    //根据采购id删除
    public int deleteZhuanZhangById(Integer id);

    //根据采购id查询
    public List<ZhuanZhang> selectZhuanZhangById(Integer id);

    /**
     * 事务处理 捕获范围 Exception
     *
     * 会记录删除的总条数 如果未被删除且未报错不会对事务进行回滚
     * @param list
     * @return 成功删除的条数
     */
    int delUsers(List<Integer> list);

}