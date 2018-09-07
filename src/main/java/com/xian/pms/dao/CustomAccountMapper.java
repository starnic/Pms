package com.xian.pms.dao;

import com.xian.pms.bean.CustomAccount;
import com.xian.pms.bean.CustomAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomAccountMapper {
    long countByExample(CustomAccountExample example);

    int deleteByExample(CustomAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomAccount record);

    int insertSelective(CustomAccount record);

    List<CustomAccount> selectByExample(CustomAccountExample example);

    CustomAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomAccount record, @Param("example") CustomAccountExample example);

    int updateByExample(@Param("record") CustomAccount record, @Param("example") CustomAccountExample example);

    int updateByPrimaryKeySelective(CustomAccount record);

    int updateByPrimaryKey(CustomAccount record);

	CustomAccount selectCustom(CustomAccount record);
}