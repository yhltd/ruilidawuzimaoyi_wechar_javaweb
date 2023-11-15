package com.myboot.mapper;

import com.myboot.pojo.CaiGouShouPiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaiGouShouPiaoMapper {

    //查询全部
    public List<CaiGouShouPiao> getAll();

    //查询需要自己审核
    public List<CaiGouShouPiao> getAllByShenHe(@Param("xinxi_tuisong") String xinxi_tuisong);

    //条件查询
    public List<CaiGouShouPiao> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kaipiao_danwei") String kaipiao_danwei,@Param("shoupiao_danwei") String shoupiao_danwei,@Param("shoupiao_zhuangtai") String shoupiao_zhuangtai);

    //增加
    public int caiGouShouPiaoAdd(CaiGouShouPiao caiGouShouPiao);

    //修改
    public int caiGouShouPiaoUpd(CaiGouShouPiao caiGouShouPiao);

    //根据采购id删除
    public int deleteByShouPiaoId(@Param("id") Integer id);

    //获取所有应收票信息
    public List<CaiGouShouPiao> selectYingShou();

    //根据采购id查询
    public List<CaiGouShouPiao> selectByShouPiaoId(@Param("id") Integer id);

    int deleteUsrById(Integer id);

    public int shouPiao(int id);


}
