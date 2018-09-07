package com.xian.pms.dao;

import com.xian.pms.bean.PmSgc;
import com.xian.pms.bean.PmSgcExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmSgcMapper {
    long countByExample(PmSgcExample example);

    int deleteByExample(PmSgcExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmSgc record);

    int insertSelective(PmSgc record);

    List<PmSgc> selectByExample(PmSgcExample example);

    PmSgc selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PmSgc record, @Param("example") PmSgcExample example);

    int updateByExample(@Param("record") PmSgc record, @Param("example") PmSgcExample example);

    int updateByPrimaryKeySelective(PmSgc record);

    int updateByPrimaryKey(PmSgc record);
}