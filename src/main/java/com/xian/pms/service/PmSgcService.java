package com.xian.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xian.pms.bean.PmSgc;
import com.xian.pms.bean.PmSgcExample;
import com.xian.pms.bean.PmSgcExample.Criteria;
import com.xian.pms.dao.PmSgcMapper;

@Service
public class PmSgcService {
	
	@Autowired
	PmSgcMapper pmSgcMapper;
	
	/**
	 * ��ѯ����Ա��
	 * 
	 */
	public List<PmSgc> getAll() {
		// TODO Auto-generated method stub
		return pmSgcMapper.selectByExample(null);
	}
	
	//���淽��
	public void saveSgc(PmSgc pmSgc) {
		pmSgcMapper.insertSelective(pmSgc);
		
	}
	
	/**
	 * �����û����Ƿ����
	 * true���������
	 * @param SgcName
	 * @return true��������� false ������
	 */
	public Boolean checkSgc(String sgcName) {
		PmSgcExample example = new PmSgcExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(sgcName);
		long count = pmSgcMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * ���չ���Աid��ѯ
	 * @param name
	 * @return
	 */
	
	public PmSgc getSgc(Integer id) {
		PmSgc sgc = pmSgcMapper.selectByPrimaryKey(id);
		return sgc;
	}
	
	/**
	 * ����
	 * @param PmSgc
	 */
	public void updateSgc(PmSgc pmSgc) {
		pmSgcMapper.updateByPrimaryKeySelective(pmSgc);
		
	}

	/**
	 * ɾ��
	 * @param id
	 */
	public void deleteSgc(Integer id) {
		
		pmSgcMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		PmSgcExample example = new PmSgcExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		pmSgcMapper.deleteByExample(example);
		
	}

	public List<PmSgc> getSgcByName(String name) {
		// TODO Auto-generated method stub
		PmSgcExample example = new PmSgcExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return pmSgcMapper.selectByExample(example);
	}
	
}
