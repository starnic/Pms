package com.xian.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xian.pms.bean.House;
import com.xian.pms.bean.HouseExample;
import com.xian.pms.bean.HouseExample.Criteria;
import com.xian.pms.dao.HouseMapper;

@Service
public class HouseService {
	
	@Autowired
	HouseMapper houseMapper;
	
	/**
	 * ��ѯ���з���
	 * 
	 */
	public List<House> getAll() {
		// TODO Auto-generated method stub
		return houseMapper.selectByExample(null);
	}
	
	//���淽��
	public void saveHouse(House house) {
		houseMapper.insertSelective(house);
		
	}
	
	/**
	 * �������Ƿ����
	 * true���������
	 * @param houseName
	 * @return true��������� false ������
	 */
	public Boolean checkHouse(String num) {
		HouseExample example = new HouseExample();
		Criteria criteria = example.createCriteria();
		criteria.andNumEqualTo(num);
		long count = houseMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * ����id��ѯ
	 * @param name
	 * @return
	 */
	
	public House getHouse(Integer id) {
		House house = houseMapper.selectByPrimaryKey(id);
		return house;
	}
	
	/**
	 * ����
	 * @param House
	 */
	public void updateHouse(House House) {
		houseMapper.updateByPrimaryKeySelective(House);
		
	}

	/**
	 * ɾ��
	 * @param id
	 */
	public void deleteHouse(Integer id) {
		
		houseMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		HouseExample example = new HouseExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		houseMapper.deleteByExample(example);
		
	}

	public List<House> getHousesByNum(String num) {
		// TODO Auto-generated method stub
		HouseExample example = new HouseExample();
		Criteria criteria = example.createCriteria();
		criteria.andNumEqualTo(num);
		return houseMapper.selectByExample(example);
	}

	public List<House> getHou(String oid) {
		HouseExample example = new HouseExample();
		Criteria criteria = example.createCriteria();
		criteria.andOwneridEqualTo(oid);
		return houseMapper.selectByExample(example);
		
	}
	
}
