package com.myboot.service;

import com.myboot.pojo.CaiGouShouPiao;

import java.util.List;

public interface CaiGouShouPiaoService {

    //查询全部
    public List<CaiGouShouPiao> getAll();

    //查询需要自己审核
    public List<CaiGouShouPiao> getAllByShenHe(String xinxi_tuisong);

    //条件查询
    public List<CaiGouShouPiao> queryList(String start_date,String stop_date,String kaipiao_danwei,String shoupiao_danwei,String shoupiao_zhuangtai);

    //增加
    public int caiGouShouPiaoAdd(CaiGouShouPiao caiGouShouPiao);

    //修改
    public int caiGouShouPiaoUpd(CaiGouShouPiao caiGouShouPiao);

    //根据采购id删除
    public int deleteByShouPiaoId(Integer id);

    //根据采购id查询
    public List<CaiGouShouPiao> selectByShouPiaoId(Integer id);

    //根据采购id查询
    public List<CaiGouShouPiao> selectYingShou();




    /**
     * 事务处理 捕获范围 Exception
     *
     * 会记录删除的总条数 如果未被删除且未报错不会对事务进行回滚
     * @param list
     * @return 成功删除的条数
     */
    int delUsers(List<Integer> list);

}