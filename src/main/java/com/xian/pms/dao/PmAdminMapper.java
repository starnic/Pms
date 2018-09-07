package com.xian.pms.dao;

import com.xian.pms.bean.PmAdmin;
import com.xian.pms.bean.PmAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmAdminMapper {
    long countByExample(PmAdminExample example);

    int deleteByExample(PmAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PmAdmin record);

    int insertSelective(PmAdmin record);

    List<PmAdmin> selectByExample(PmAdminExample example);

    PmAdmin selectByPrimaryKey(Integer id);
    
    PmAdmin selectAdmin(PmAdmin record);
    
    int updateByExampleSelective(@Param("record") PmAdmin record, @Param("example") PmAdminExample example);

    int updateByExample(@Param("record") PmAdmin record, @Param("example") PmAdminExample example);

    int updateByPrimaryKeySelective(PmAdmin record);

    int updateByPrimaryKey(PmAdmin record);
}