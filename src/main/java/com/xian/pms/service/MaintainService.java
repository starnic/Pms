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
	 * 查询所有报修
	 * 
	 */
	public List<Maintain> getAll() {
		// TODO Auto-generated method stub
		return maintainMapper.selectByExample(null);
	}
	
	//报修保存方法
	public void saveMaintain(Maintain maintain) {
		maintainMapper.insertSelective(maintain);
		
	}

	
	/**
	 * 按照管理员id查询
	 * @param name
	 * @return
	 */
	
	public Maintain getMaintain(Integer id) {
		Maintain maintain = maintainMapper.selectByPrimaryKey(id);
		return maintain;
	}
	
	/**
	 * 报修更新
	 * @param Maintain
	 */
	public void updateMaintain(Maintain maintain) {
		maintainMapper.updateByPrimaryKeySelective(maintain);
		
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteMaintain(Integer id) {
		
		maintainMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
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
