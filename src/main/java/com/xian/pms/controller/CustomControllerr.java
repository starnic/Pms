package com.xian.pms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.xian.pms.bean.CustomAccount;
import com.xian.pms.bean.Msg;
import com.xian.pms.service.CustomService;

//������ɾ�Ĳ�
@Controller
public class CustomControllerr {

	@Autowired
	CustomService customService;
	
	/**
	 * ���� ����ɾ��  ����һ
	 * ����ɾ��  1-2-3
	 * ����ɾ��  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/custom/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteCustom(@PathVariable("ids") String ids) {
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			customService.deleteBatch(del_ids);
		//����ɾ��
		}else {
			int id = Integer.parseInt(ids);
			customService.deleteCustom(id);
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
	 * @param customAccount
	 * @return
	 */
	@RequestMapping( value="/custom/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveCustom(CustomAccount customAccount) {
		customService.updateCustom(customAccount);
		return Msg.success();
		
	}
	
	/**
	 * ����id��ѯҵ��
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/custom/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getCoustom(@PathVariable("id") Integer id) {
		
		CustomAccount custom = customService.getCustom(id);
		
		return Msg.success().add("custom", custom);
		
	}
	
	/**
	 * ����û����Ƿ����
	 * @param adminName
	 * @return
	 */
	@RequestMapping("/checkCustom")
	@ResponseBody
	public Msg checkCustom(@RequestParam("name") String customName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!customName.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-5λ���Ļ���6-16λӢ�����ֵ����");
		}
		
		//���ݿ��û����ظ�У��
		boolean b = customService.checkCustom(customName);
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
	 * @param CustomAccount
	 * @return
	 */
	
	//��������Ա
	@RequestMapping(value="/custom", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveCustom(@Valid CustomAccount customAccount, BindingResult result) {
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
			customService.saveCustom(customAccount);
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
	@RequestMapping("/customs")
	@ResponseBody
	public Msg getCuustomWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<CustomAccount> custom = customService.getAll();
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(custom, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/customs/{name}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getCustoms(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("name") String name) {
		
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<CustomAccount> custom = customService.getCustomByName(name);
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(custom, 5);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * ҵ����¼
	 * @param custom
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/checkLoginc")
	@ResponseBody
	public Msg checkLoginc(@RequestParam("name") String adminName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		/*String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!adminName.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-5λ���Ļ���6-16λӢ�����ֵ����");
		}*/
		
		//���ݿ��û����Ƿ����У��
		boolean b = customService.checkCustom(adminName);
		if(!b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}
		
	}
	
	
	@RequestMapping(value="/customLogin", method=RequestMethod.POST)
	@ResponseBody
	public Msg customLogin(@Valid CustomAccount customAccount, HttpServletRequest request) {
		CustomAccount custom = customService.customLogin(customAccount);
		if(custom != null) {
			request.getSession().setAttribute("LOGIN_CUSTOM", custom);
			return Msg.success();
		}
		return Msg.fail();
	}
	
	
	@RequestMapping(value="/customss/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getCustom(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("id") Integer id) {
		
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<CustomAccount> custom = customService.getCustomById(id);
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(custom, 5);
		return Msg.success().add("pageInfo", page);
	}
	
}
