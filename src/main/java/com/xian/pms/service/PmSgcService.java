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
	 * 查询所有员工
	 * 
	 */
	public List<PmSgc> getAll() {
		// TODO Auto-generated method stub
		return pmSgcMapper.selectByExample(null);
	}
	
	//保存方法
	public void saveSgc(PmSgc pmSgc) {
		pmSgcMapper.insertSelective(pmSgc);
		
	}
	
	/**
	 * 检验用户名是否可用
	 * true：代表可用
	 * @param SgcName
	 * @return true：代表可用 false 不可用
	 */
	public Boolean checkSgc(String sgcName) {
		PmSgcExample example = new PmSgcExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(sgcName);
		long count = pmSgcMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * 按照管理员id查询
	 * @param name
	 * @return
	 */
	
	public PmSgc getSgc(Integer id) {
		PmSgc sgc = pmSgcMapper.selectByPrimaryKey(id);
		return sgc;
	}
	
	/**
	 * 更新
	 * @param PmSgc
	 */
	public void updateSgc(PmSgc pmSgc) {
		pmSgcMapper.updateByPrimaryKeySelective(pmSgc);
		
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteSgc(Integer id) {
		
		pmSgcMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
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
