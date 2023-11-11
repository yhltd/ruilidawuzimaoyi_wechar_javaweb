package com.myboot.mapper;

import com.myboot.pojo.CaiGouDingDan;
import com.myboot.pojo.ShouZhiMingXi;
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
    public List<ShouZhiMingXi> shouzhiAdd(@Param("dianpu") String dianpu,@Param("danju_leixing") String danju_leixing,@Param("danju_bianhao") String danju_bianhao,@Param("shouzhi_bianhao") String shouzhi_bianhao,@Param("shouzhi_riqi") String shouzhi_riqi,@Param("jizhangren") String jizhangren,@Param("jizhang_type") String jizhang_type,@Param("jizhang_zhanghu") String jizhang_zhanghu,@Param("jizhang_jine") String jizhang_jine,@Param("kedi_shuie") String kedi_shuie,@Param("zhaiyao") String zhaiyao,@Param("shouzhi_type") String shouzhi_type);

    //收支修改
    public List<ShouZhiMingXi> shouzhiUpd(@Param("dianpu") String dianpu,@Param("danju_leixing") String danju_leixing,@Param("danju_bianhao") String danju_bianhao,@Param("shouzhi_bianhao") String shouzhi_bianhao,@Param("shouzhi_riqi") String shouzhi_riqi,@Param("jizhangren") String jizhangren,@Param("jizhang_type") String jizhang_type,@Param("jizhang_zhanghu") String jizhang_zhanghu,@Param("jizhang_jine") String jizhang_jine,@Param("kedi_shuie") String kedi_shuie,@Param("zhaiyao") String zhaiyao,@Param("shouzhi_type") String shouzhi_type,@Param("id") Integer id);

    //根据id删除
    public boolean deleteById(Integer id);

    //根据id查询
    public List<ShouZhiMingXi> selectById(Integer id);


}
