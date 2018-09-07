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
	 * ��ѯ���й���Ա
	 * 
	 */
	public List<PmAdmin> getAll() {
		// TODO Auto-generated method stub
		return pmAdminMapper.selectByExample(null);
	}
	
	//����Ա���淽��
	public void saveAdmin(PmAdmin pmAdmin) {
		pmAdminMapper.insertSelective(pmAdmin);
		
	}
	
	/**
	 * �����û����Ƿ����
	 * true���������
	 * @param adminName
	 * @return true��������� false ������
	 */
	public Boolean checkAdm(String adminName) {
		PmAdminExample example = new PmAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(adminName);
		long count = pmAdminMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * ���չ���Աid��ѯ
	 * @param name
	 * @return
	 */
	
	public PmAdmin getAdmin(Integer id) {
		PmAdmin admin = pmAdminMapper.selectByPrimaryKey(id);
		return admin;
	}
	
	/**
	 * ����Ա����
	 * @param pmAdmin
	 */
	public void updateAdmin(PmAdmin pmAdmin) {
		pmAdminMapper.updateByPrimaryKeySelective(pmAdmin);
		
	}

	/**
	 * ����Աɾ��
	 * @param id
	 */
	public void deleteAdmin(Integer id) {
		
		pmAdminMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
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
