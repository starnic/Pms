package com.xian.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xian.pms.bean.CustomAccount;
import com.xian.pms.bean.CustomAccountExample;
import com.xian.pms.bean.PmAdmin;
import com.xian.pms.bean.PmAdminExample;
import com.xian.pms.bean.PmAdminExample.Criteria;
import com.xian.pms.dao.CustomAccountMapper;
import com.xian.pms.dao.PmAdminMapper;

@Service
public class PmAdminService {
	
	@Autowired
	PmAdminMapper pmAdminMapper;
	
	@Autowired
	CustomAccountMapper customAccountMapper;
	
	/**
	 * 查询所有管理员
	 * 
	 */
	public List<PmAdmin> getAll() {
		// TODO Auto-generated method stub
		return pmAdminMapper.selectByExample(null);
	}
	
	//管理员保存方法
	public void saveAdmin(PmAdmin pmAdmin) {
		pmAdminMapper.insertSelective(pmAdmin);
		
	}
	
	/**
	 * 检验用户名是否可用
	 * true：代表可用
	 * @param adminName
	 * @return true：代表可用 false 不可用
	 */
	public Boolean checkAdm(String adminName) {
		PmAdminExample example = new PmAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(adminName);
		long count = pmAdminMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * 按照管理员id查询
	 * @param name
	 * @return
	 */
	
	public PmAdmin getAdmin(Integer id) {
		PmAdmin admin = pmAdminMapper.selectByPrimaryKey(id);
		return admin;
	}
	
	/**
	 * 管理员更新
	 * @param pmAdmin
	 */
	public void updateAdmin(PmAdmin pmAdmin) {
		pmAdminMapper.updateByPrimaryKeySelective(pmAdmin);
		
	}

	/**
	 * 管理员删除
	 * @param id
	 */
	public void deleteAdmin(Integer id) {
		
		pmAdminMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		PmAdminExample example = new PmAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		pmAdminMapper.deleteByExample(example);
		
	}

	public List<PmAdmin> getAdminByName(String name) {
		// TODO Auto-generated method stub
		PmAdminExample example = new PmAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		return pmAdminMapper.selectByExample(example);
	}

	
	public PmAdmin adminLogin(PmAdmin pmAdmin) {
		PmAdmin admin = pmAdminMapper.selectAdmin(pmAdmin);
		return admin;
	}

}
