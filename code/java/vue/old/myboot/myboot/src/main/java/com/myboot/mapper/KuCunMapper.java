package com.myboot.mapper;

import com.myboot.pojo.KuCun;
import com.myboot.pojo.XiaoShouDingDan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KuCunMapper {

    //查询全部
    public List<KuCun> getKuCun();

    //查询指定仓库
    public List<KuCun> getKuCunByCangKu(@Param("cangku") String cangku);


}
