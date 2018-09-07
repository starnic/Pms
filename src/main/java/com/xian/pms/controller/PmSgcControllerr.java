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
import com.xian.pms.bean.PmSgc;
import com.xian.pms.service.PmSgcService;

//������ɾ�Ĳ�
@Controller
public class PmSgcControllerr {

	@Autowired
	PmSgcService pmSgcService;
	
	/**
	 * ���� ����ɾ��  ����һ
	 * ����ɾ��  1-2-3
	 * ����ɾ��  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/sgc/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteSgc(@PathVariable("ids") String ids) {
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			pmSgcService.deleteBatch(del_ids);
		//����ɾ��
		}else {
			int id = Integer.parseInt(ids);
			pmSgcService.deleteSgc(id);
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
	 * @param PmSgc
	 * @return
	 */
	@RequestMapping( value="/sgc/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveSgc(PmSgc pmSgc) {
		pmSgcService.updateSgc(pmSgc);
		return Msg.success();
		
	}
	
	/**
	 * ����id��ѯ����Ա
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/sgc/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getSgc(@PathVariable("id") Integer id) {
		
		PmSgc sgc = pmSgcService.getSgc(id);
		
		return Msg.success().add("sgc", sgc);
		
	}
	
	/**
	 * ����û����Ƿ����
	 * @param sgcName
	 * @return
	 */
	@RequestMapping("/checkSgc")
	@ResponseBody
	public Msg checkSgc(@RequestParam("name") String sgcName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!sgcName.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-5λ���Ļ���6-16λӢ�����ֵ����");
		}
		
		//���ݿ��û����ظ�У��
		boolean b = pmSgcService.checkSgc(sgcName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}
		
	}
	
	/**
	 * Ա������
	 * 1.֧��JSR303У��
	 * ���� hibernate validator
	 * @param pmsgc
	 * @return
	 */
	
	//��������Ա
	@RequestMapping(value="/sgc", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveSgc(@Valid PmSgc pmSgc, BindingResult result) {
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
			pmSgcService.saveSgc(pmSgc);
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
	@RequestMapping("/sgcs")
	@ResponseBody
	public Msg getSgcsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<PmSgc> sgcs = pmSgcService.getAll();
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(sgcs, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/sgcs/{name}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getSgcs(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("name") String name) {
		
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<PmSgc> sgcs = pmSgcService.getSgcByName(name);
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(sgcs, 5);
		return Msg.success().add("pageInfo", page);
	}

}
