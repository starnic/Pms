package com.xian.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xian.pms.bean.Notice;
import com.xian.pms.bean.NoticeExample;
import com.xian.pms.bean.NoticeExample.Criteria;
import com.xian.pms.dao.NoticeMapper;

@Service
public class NoticeService {
	
	@Autowired
	NoticeMapper noticeMapper;
	
	/**
	 * ��ѯ���й���
	 * 
	 */
	public List<Notice> getAll() {
		// TODO Auto-generated method stub
		return noticeMapper.selectByExample(null);
	}
	
	//���汣�淽��
	public void saveNotice(Notice Notice) {
		noticeMapper.insertSelective(Notice);
		
	}
	
	
	/**
	 * ���չ���Աid��ѯ
	 * @param name
	 * @return
	 */
	
	public Notice getNotice(Integer id) {
		Notice Notice = noticeMapper.selectByPrimaryKey(id);
		return Notice;
	}
	
	/**
	 * ����
	 * @param Notice
	 */
	public void updateNotice(Notice Notice) {
		noticeMapper.updateByPrimaryKeySelective(Notice);
		
	}

	/**
	 * ɾ��
	 * @param id
	 */
	public void deleteNotice(Integer id) {
		
		noticeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		NoticeExample example = new NoticeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		noticeMapper.deleteByExample(example);
		
	}

	public List<Notice> getNoticeByName(String title) {
		// TODO Auto-generated method stub
		NoticeExample example = new NoticeExample();
		Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		return noticeMapper.selectByExample(example);
	}
	
}
