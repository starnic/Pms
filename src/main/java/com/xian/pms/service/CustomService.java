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
	 * 查询所有业主
	 * 
	 */
	public List<CustomAccount> getAll() {
		// TODO Auto-generated method stub
		return customAccountMapper.selectByExample(null);
	}
	
	//业主保存方法
	public void saveCustom(CustomAccount customAccount) {
		customAccountMapper.insertSelective(customAccount);
		
	}
	
	/**
	 * 检验用户名是否可用
	 * true：代表可用
	 * @param customName
	 * @return true：代表可用 false 不可用
	 */
	public Boolean checkCustom(String name) {
		CustomAccountExample example = new CustomAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		long count = customAccountMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * 按照id查询
	 * @param name
	 * @return
	 */
	
	public CustomAccount getCustom(Integer id) {
		CustomAccount customAccount = customAccountMapper.selectByPrimaryKey(id);
		return customAccount;
	}
	
	/**
	 * 更新
	 * @param customAccount
	 */
	public void updateCustom(CustomAccount customAccount) {
		customAccountMapper.updateByPrimaryKeySelective(customAccount);
		
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteCustom(Integer id) {
		
		customAccountMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
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
