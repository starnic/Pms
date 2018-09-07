package com.xian.pms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xian.pms.bean.Msg;
import com.xian.pms.bean.Notice;
import com.xian.pms.service.NoticeService;

//������ɾ�Ĳ�
@Controller
public class NoticeControllerr {

	@Autowired
	NoticeService noticeService;
	
	/**
	 * ���� ����ɾ��  ����һ
	 * ����ɾ��  1-2-3
	 * ����ɾ��  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/notice/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteNotice(@PathVariable("ids") String ids) {
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			noticeService.deleteBatch(del_ids);
		//����ɾ��
		}else {
			int id = Integer.parseInt(ids);
			noticeService.deleteNotice(id);
		}
		return Msg.success();
		
	}
	
	
	/**
	 * ���·���
	 *ajax����ֱ�ӷ���put����
	 * 
	 * ���������
	 * 
	 * ����Ҫ��֧��ֱ�ӷ���PUT֮�������Ҫ��װ�������е�����
	 * ������  HttpPutFormContentFilter 
	 * ���ã����ǽ��������е����ݽ�����װ��һ��map��request�����°�װ
	 * request�����°�װ��reqyest.getParameter()����д���ͻ���Լ���װ��map��ȡ����
	 * @param Notice
	 * @return
	 */
	@RequestMapping( value="/notice/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveNotice(Notice notice) {
		noticeService.updateNotice(notice);
		return Msg.success();
		
	}
	
	/**
	 * ����id��ѯ����Ա
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/notice/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getNotice(@PathVariable("id") Integer id) {
		
		Notice notice = noticeService.getNotice(id);
		
		return Msg.success().add("notice", notice);
		
	}
	
	/**
	 * ����û����Ƿ����
	 * @param noticeName
	 * @return
	 */
	@RequestMapping("/checkNotice")
	@ResponseBody
	public Msg checkNotice(@RequestParam("name") String noticeName) {
		//���ж��û����Ƿ��ǺϷ��ı���ʽ
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!noticeName.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-5λ���Ļ���6-16λӢ�����ֵ����");
		}
		return Msg.success();
		
		//���ݿ��û����ظ�У��
		/*boolean b = noticeService.checkNotice(noticeName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}*/
		
	}
	
	/**
	 * Ա������
	 * 1.֧��JSR303У��
	 * ���� hibernate validator
	 * @param pmnotice
	 * @return
	 */
	
	//��������Ա
	@RequestMapping(value="/notice", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveNotice(@Valid Notice notice, BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ�ܷ���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("������ֶ�����" + fieldError.getField());
				System.out.println("������Ϣ��" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			noticeService.saveNotice(notice);
			return Msg.success();
		}
		
	}
	
	/**
	 * ��ѯ���� ��ҳ��ѯ
	 * 
	 * @return
	 * 
	 * 		����jackson��
	 */
	@RequestMapping("/notices")
	@ResponseBody
	public Msg getNoticesWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<Notice> notices = noticeService.getAll();
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(notices, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/notices/{name}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getNotices(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("name") String name) {
		
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<Notice> notices = noticeService.getNoticeByName(name);
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(notices, 5);
		return Msg.success().add("pageInfo", page);
	}

}