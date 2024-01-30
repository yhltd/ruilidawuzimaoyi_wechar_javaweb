package com.myboot.mapper;

import com.myboot.pojo.JiZhang;
import com.myboot.pojo.KuCun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JiZhangMapper {

    //查询指定仓库
    public List<JiZhang> getJiZhang(String start_date, String stop_date, String dianpu, String jizhang_danwei, String jizhang_zhanghu);

}
