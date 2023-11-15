package com.myboot.service;

import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.XiaoShouKaiPiao;

import java.util.List;

public interface XiaoShouKaiPiaoService {

    //查询全部
    public List<XiaoShouKaiPiao> getAll();

    //查询需要自己审核
    public List<XiaoShouKaiPiao> getAllByShenHe(String xinxi_tuisong);

    //条件查询
    public List<XiaoShouKaiPiao> queryList(String start_date,String stop_date,String shoupiao_danwei, String kaipiao_danwei,String kaipiao_zhuangtai);

    //增加
    public int kaiPiaoAdd(XiaoShouKaiPiao xiaoShouKaiPiao);

    //修改
    public int kaiPiaoUpd(XiaoShouKaiPiao xiaoShouKaiPiao);

    //根据采购id删除
    public int deleteXiaoShouById(Integer id);

    /**
     * 事务处理 捕获范围 Exception
     *
     * 会记录删除的总条数 如果未被删除且未报错不会对事务进行回滚
     * @param list
     * @return 成功删除的条数
     */
    int delUsers(List<Integer> list);

    //根据采购id查询
    public List<XiaoShouKaiPiao> selectXiaoShouById(Integer id);

    public List<XiaoShouKaiPiao> selectYingKai();

    public int kaiPiao(List<Integer> list);

}