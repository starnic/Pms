package com.xian.pms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xian.pms.bean.Inspection;
import com.xian.pms.bean.InspectionExample;
import com.xian.pms.bean.InspectionExample.Criteria;
import com.xian.pms.bean.PmSgcExample;
import com.xian.pms.dao.InspectionMapper;
import com.xian.pms.dao.PmSgcMapper;

@Service
public class InspectionService {
	
	@Autowired
	InspectionMapper inspectionMapper;
	
	@Autowired
	PmSgcMapper pmSgcMapper;
	
	/**
	 * 查询所有记录
	 * 
	 */
	public List<Inspection> getAll() {
		// TODO Auto-generated method stub
		return inspectionMapper.selectByExample(null);
	}
	
	//保存方法
	public void saveInspection(Inspection inspection) {
		inspectionMapper.insertSelective(inspection);
		
	}
	
	/**
	 * 检验名是否可用
	 * true：代表可用
	 * @param InspectionName
	 * @return true：代表可用 false 不可用
	 */
	public Boolean checkInspection(String inspectionName) {
		PmSgcExample example = new PmSgcExample();
		com.xian.pms.bean.PmSgcExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(inspectionName);
		long count = pmSgcMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * 按照id查询
	 * @param name
	 * @return
	 */
	
	public Inspection getInspection(Integer id) {
		Inspection inspection = inspectionMapper.selectByPrimaryKey(id);
		return inspection;
	}
	
	/**
	 * 更新
	 * @param Inspection
	 */
	public void updateInspection(Inspection inspection) {
		inspectionMapper.updateByPrimaryKeySelective(inspection);
		
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInspection(Integer id) {
		
		inspectionMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		InspectionExample example = new InspectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		inspectionMapper.deleteByExample(example);
		
	}

	public List<Inspection> getInspectionByName(Date time) {
		// TODO Auto-generated method stub
		InspectionExample example = new InspectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andItimeEqualTo(time);
		return inspectionMapper.selectByExample(example);
	}
	
}
