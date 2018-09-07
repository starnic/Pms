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
	 * ��ѯ���м�¼
	 * 
	 */
	public List<Inspection> getAll() {
		// TODO Auto-generated method stub
		return inspectionMapper.selectByExample(null);
	}
	
	//���淽��
	public void saveInspection(Inspection inspection) {
		inspectionMapper.insertSelective(inspection);
		
	}
	
	/**
	 * �������Ƿ����
	 * true���������
	 * @param InspectionName
	 * @return true��������� false ������
	 */
	public Boolean checkInspection(String inspectionName) {
		PmSgcExample example = new PmSgcExample();
		com.xian.pms.bean.PmSgcExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(inspectionName);
		long count = pmSgcMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * ����id��ѯ
	 * @param name
	 * @return
	 */
	
	public Inspection getInspection(Integer id) {
		Inspection inspection = inspectionMapper.selectByPrimaryKey(id);
		return inspection;
	}
	
	/**
	 * ����
	 * @param Inspection
	 */
	public void updateInspection(Inspection inspection) {
		inspectionMapper.updateByPrimaryKeySelective(inspection);
		
	}

	/**
	 * ɾ��
	 * @param id
	 */
	public void deleteInspection(Integer id) {
		
		inspectionMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
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
