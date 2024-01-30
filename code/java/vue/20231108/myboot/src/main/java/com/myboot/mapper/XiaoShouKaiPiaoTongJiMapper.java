package com.myboot.mapper;

import com.myboot.pojo.JiZhang;
import com.myboot.pojo.XiaoShouKaiPiaoTongJi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XiaoShouKaiPiaoTongJiMapper {

    //根据核算单位汇总
    public List<XiaoShouKaiPiaoTongJi> getTongJiByDanWei(String start_date, String stop_date);

    //根据客户汇总
    public List<XiaoShouKaiPiaoTongJi> getTongJiByKeHu(String start_date, String stop_date);

}
