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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xian.pms.bean.CustomAccount;
import com.xian.pms.bean.Msg;
import com.xian.pms.bean.PmAdmin;
import com.xian.pms.service.CustomService;
import com.xian.pms.service.PmAdminService;

//������ɾ�Ĳ�
@Controller
public class AdminControllerr {

	@Autowired
	PmAdminService pmAdminService;
	
	
	@Autowired
	CustomService customService;
	
	/**
	 * ���� ����ɾ��  ����һ
	 * ����ɾ��  1-2-3
	 * ����ɾ��  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteAdmin(@PathVariable("ids") String ids) {
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			pmAdminService.deleteBatch(del_ids);
		//����ɾ��
		}else {
			int id = Integer.parseInt(ids);
			pmAdminService.deleteAdmin(id);
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
	 * @param pmAdmin
	 * @return
	 */
	@RequestMapping( value="/admin/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveAdmin(PmAdmin pmAdmin) {
		pmAdminService.updateAdmin(pmAdmin);
		return Msg.success();
		
	}
	
	/**
	 * ����id��ѯ����Ա
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/admin/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getAdmin(@PathVariable("id") Integer id) {
		
		PmAdmin admin = pmAdminService.getAdmin(id);
		
		return Msg.success().add("admin", admin);
		
	}
	
	/**
	 * ����û����Ƿ����
	 * @param adminName
	 * @return
	 */
	@RequestMapping("/checkAdmin")
	@ResponseBody
	public Msg checkAdmin(@RequestParam("name") String adminName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!adminName.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-5λ���Ļ���6-16λӢ�����ֵ����");
		}
		
		//���ݿ��û����ظ�У��
		boolean b = pmAdminService.checkAdm(adminName);
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
	 * @param pmAdmin
	 * @return
	 */
	
	//��������Ա
	@RequestMapping(value="/admin", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveAdmin(@Valid PmAdmin pmAdmin, BindingResult result) {
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
			pmAdminService.saveAdmin(pmAdmin);
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
	@RequestMapping("/admins")
	@ResponseBody
	public Msg getAdminsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<PmAdmin> adms = pmAdminService.getAll();
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(adms, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/admins/{name}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getAdmins(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("name") String name) {
		
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<PmAdmin> adms = pmAdminService.getAdminByName(name);
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(adms, 5);
		return Msg.success().add("pageInfo", page);
	}
	
	
	
	
	/**
	 * ����Ա��¼
	 * @param adminName
	 * @return
	 */
	
	@RequestMapping("/checkLogin")
	@ResponseBody
	public Msg checkLogin(@RequestParam("name") String adminName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		/*String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!adminName.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-5λ���Ļ���6-16λӢ�����ֵ����");
		}*/
		
		//���ݿ��û����ظ�У��
		boolean b = pmAdminService.checkAdm(adminName);
		if(!b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}
		
	}
	@RequestMapping(value="/adminLogin", method=RequestMethod.POST)
	@ResponseBody
	public Msg adminLogin(@Valid PmAdmin pmAdmin, HttpServletRequest request) {
		PmAdmin admin = pmAdminService.adminLogin(pmAdmin);
		if(admin != null) {
			request.getSession().setAttribute("LOGIN_ADMIN", admin);
			return Msg.success();
		}
		return Msg.fail();
	}
	
}
