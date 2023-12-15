package com.myboot.mapper;

import com.myboot.pojo.XiaoShouBaoJia;
import com.myboot.pojo.XiaoShouBaoJiaAdd;
import com.myboot.pojo.XiaoShouChuKu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XiaoShouBaoJiaMapper {

    //查询全部
    public List<XiaoShouBaoJia> getAll();

    public List<XiaoShouBaoJia> getAllByName(String yewuyuan);

    //查询需要自己审核
    public List<XiaoShouBaoJia> getAllByShenHe(@Param("shenhe") String shenhe);

    //条件查询
    public List<XiaoShouBaoJia> queryList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);


    public List<XiaoShouBaoJia> queryListByName(@Param("yewuyuan") String yewuyuan,@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("kehu") String kehu,@Param("shenhe_zhuangtai") String shenhe_zhuangtai);

    //增加
    public int baoJiaAdd(XiaoShouBaoJia xiaoShouBaoJia);

    //修改
    public int baoJiaUpd(XiaoShouBaoJia xiaoShouBaoJia);

    //根据采购id删除
    public int deleteBaoJiaById(@Param("id") Integer id);

    //根据采购id查询
    public List<XiaoShouBaoJia> selectBaoJiaById(@Param("id") Integer id);

    int deleteUsrById(Integer id);

    public List<XiaoShouBaoJia> selectMaxDanHao();

    public List<XiaoShouBaoJia> shenheList(String name);

    public int baoJiaShenHe(int id,String type);
    //  ztt  审核
    public int baoJiaShenHe(int id,String shenheList,String shenheZhuangtai);
//    ztt end

}
