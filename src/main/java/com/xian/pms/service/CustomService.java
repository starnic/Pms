  package com.xian.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xian.pms.bean.CustomAccount;
import com.xian.pms.bean.CustomAccountExample;
import com.xian.pms.bean.CustomAccountExample.Criteria;
import com.xian.pms.dao.CustomAccountMapper;


@Service
public class CustomService {
	
	@Autowired
	CustomAccountMapper customAccountMapper;
	
	/**
	 * ��ѯ����ҵ��
	 * 
	 */
	public List<CustomAccount> getAll() {
		// TODO Auto-generated method stub
		return customAccountMapper.selectByExample(null);
	}
	
	//ҵ�����淽��
	public void saveCustom(CustomAccount customAccount) {
		customAccountMapper.insertSelective(customAccount);
		
	}
	
	/**
	 * �����û����Ƿ����
	 * true���������
	 * @param customName
	 * @return true��������� false ������
	 */
	public Boolean checkCustom(String name) {
		CustomAccountExample example = new CustomAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		long count = customAccountMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * ����id��ѯ
	 * @param name
	 * @return
	 */
	
	public CustomAccount getCustom(Integer id) {
		CustomAccount customAccount = customAccountMapper.selectByPrimaryKey(id);
		return customAccount;
	}
	
	/**
	 * ����
	 * @param customAccount
	 */
	public void updateCustom(CustomAccount customAccount) {
		customAccountMapper.updateByPrimaryKeySelective(customAccount);
		
	}

	/**
	 * ɾ��
	 * @param id
	 */
	public void deleteCustom(Integer id) {
		
		customAccountMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		CustomAccountExample example = new CustomAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		customAccountMapper.deleteByExample(example);
		
	}

	public List<CustomAccount> getCustomByName(String name) {
		// TODO Auto-generated method stub
		CustomAccountExample example = new CustomAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return customAccountMapper.selectByExample(example);
	}

	public CustomAccount customLogin(CustomAccount customAccount) {
		// TODO Auto-generated method stub
		CustomAccount custom = customAccountMapper.selectCustom(customAccount);
		return custom;
	}

	public List<CustomAccount> getCustomById(Integer id) {
		// TODO Auto-generated method stub
		CustomAccountExample example = new CustomAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		return customAccountMapper.selectByExample(example);
	}
	
}
