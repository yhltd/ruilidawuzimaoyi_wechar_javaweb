package com.myboot.mapper;

import com.myboot.pojo.CaiGouShouPiao;
import com.myboot.pojo.XiaoShouDingDan;
import com.myboot.pojo.XiaoShouKaiPiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouKaiPiaoMapper {

    //查询全部
    public List<XiaoShouKaiPiao> getAll();

    //查询需要自己审核
    public List<XiaoShouKaiPiao> getAllByShenHe(@Param("xinxi_tuisong") String xinxi_tuisong);

    //条件查询
    public List<XiaoShouKaiPiao> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("shoupiao_danwei") String shoupiao_danwei,@Param("kaipiao_danwei") String kaipiao_danwei,@Param("kaipiao_zhuangtai") String kaipiao_zhuangtai);

    //增加
    public int kaiPiaoAdd(XiaoShouKaiPiao xiaoShouKaiPiao);

    //修改
    public int kaiPiaoUpd(XiaoShouKaiPiao xiaoShouKaiPiao);

    //根据销售id删除
    public int deleteXiaoShouById(@Param("id") Integer id);

    //根据销售id查询
    public List<XiaoShouKaiPiao> selectXiaoShouById(@Param("id") Integer id);

    int deleteUsrById(Integer id);

    public List<XiaoShouKaiPiao> selectYingKai();


}
