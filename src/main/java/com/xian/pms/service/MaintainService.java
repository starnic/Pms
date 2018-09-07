package com.xian.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xian.pms.bean.Maintain;
import com.xian.pms.bean.MaintainExample;
import com.xian.pms.bean.MaintainExample.Criteria;
import com.xian.pms.dao.MaintainMapper;

@Service
public class MaintainService {
	
	@Autowired
	MaintainMapper maintainMapper;
	
	/**
	 * ��ѯ���б���
	 * 
	 */
	public List<Maintain> getAll() {
		// TODO Auto-generated method stub
		return maintainMapper.selectByExample(null);
	}
	
	//���ޱ��淽��
	public void saveMaintain(Maintain maintain) {
		maintainMapper.insertSelective(maintain);
		
	}

	
	/**
	 * ���չ���Աid��ѯ
	 * @param name
	 * @return
	 */
	
	public Maintain getMaintain(Integer id) {
		Maintain maintain = maintainMapper.selectByPrimaryKey(id);
		return maintain;
	}
	
	/**
	 * ���޸���
	 * @param Maintain
	 */
	public void updateMaintain(Maintain maintain) {
		maintainMapper.updateByPrimaryKeySelective(maintain);
		
	}

	/**
	 * ɾ��
	 * @param id
	 */
	public void deleteMaintain(Integer id) {
		
		maintainMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		MaintainExample example = new MaintainExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		maintainMapper.deleteByExample(example);
		
	}

	public List<Maintain> getMaintainByName(String number) {
		// TODO Auto-generated method stub
		MaintainExample example = new MaintainExample();
		Criteria criteria = example.createCriteria();
		criteria.andHomesbumberEqualTo(number);
		return maintainMapper.selectByExample(example);
	}
	
}
