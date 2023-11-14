package com.myboot.mapper;

import com.myboot.pojo.CaiGouRuKu;
import com.myboot.pojo.ShouZhiMingXi;
import com.myboot.pojo.ShouZhiMingXiAdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShouZhiMingXiMapper {

    //查询全部收入
    public List<ShouZhiMingXi> getShouRu();

    //查询个人收入
    public List<ShouZhiMingXi> getShouRuByName(String jizhangren);

    //查询全部支出
    public List<ShouZhiMingXi> getZhiChu();

    //查询个人支出
    public List<ShouZhiMingXi> getZhiChuByName(String jizhangren);

    //条件查询收入
    public List<ShouZhiMingXi> queryShouRuList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("dianpu") String dianpu,@Param("jizhangren") String jizhangren,@Param("jizhang_zhanghu") String jizhang_zhanghu);

    //条件查询支出
    public List<ShouZhiMingXi> queryZhiChuList(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("dianpu") String dianpu,@Param("jizhangren") String jizhangren,@Param("jizhang_zhanghu") String jizhang_zhanghu);

    //条件查询收入
    public List<ShouZhiMingXi> queryShouRuListByName(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("dianpu") String dianpu,@Param("jizhangren") String jizhangren,@Param("jizhang_zhanghu") String jizhang_zhanghu,@Param("yuangong_name") String yuangong_name);

    //条件查询支出
    public List<ShouZhiMingXi> queryZhiChuListByName(@Param("start_date") String start_date,@Param("stop_date") String stop_date,@Param("dianpu") String dianpu,@Param("jizhangren") String jizhangren,@Param("jizhang_zhanghu") String jizhang_zhanghu,@Param("yuangong_name") String yuangong_name);

    //收支增加
    public int shouzhiAdd(ShouZhiMingXi shouZhiMingXi);

    //收支修改
    public int shouzhiUpd(ShouZhiMingXi shouZhiMingXi);

    //根据id删除
    public int deleteById(Integer id);

    //根据id查询
    public List<ShouZhiMingXi> selectById(Integer id);

    int deleteUsrById(Integer id);

    //查询最大单号
    public List<ShouZhiMingXi> selectMaxDanHao();


}
