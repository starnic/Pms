package com.xian.pms.dao;

import com.xian.pms.bean.Inspection;
import com.xian.pms.bean.InspectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InspectionMapper {
    long countByExample(InspectionExample example);

    int deleteByExample(InspectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Inspection record);

    int insertSelective(Inspection record);

    List<Inspection> selectByExample(InspectionExample example);

    Inspection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Inspection record, @Param("example") InspectionExample example);

    int updateByExample(@Param("record") Inspection record, @Param("example") InspectionExample example);

    int updateByPrimaryKeySelective(Inspection record);

    int updateByPrimaryKey(Inspection record);
}