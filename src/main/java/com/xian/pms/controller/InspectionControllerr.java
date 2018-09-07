package com.xian.pms.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.xian.pms.bean.Inspection;
import com.xian.pms.bean.Msg;
import com.xian.pms.service.InspectionService;

//������ɾ�Ĳ�
@Controller
public class InspectionControllerr {

	@Autowired
	InspectionService inspectionService;
	
	/**
	 * ���� ����ɾ��  ����һ
	 * ����ɾ��  1-2-3
	 * ����ɾ��  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/inspection/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteInspection(@PathVariable("ids") String ids) {
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			inspectionService.deleteBatch(del_ids);
		//����ɾ��
		}else {
			int id = Integer.parseInt(ids);
			inspectionService.deleteInspection(id);
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
	 * @param Inspection
	 * @return
	 */
	@RequestMapping( value="/inspection/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveInspection(Inspection inspection) {
		inspectionService.updateInspection(inspection);
		return Msg.success();
		
	}
	
	/**
	 * ����id��ѯ����Ա
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/inspection/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getInspection(@PathVariable("id") Integer id) {
		
		Inspection inspection = inspectionService.getInspection(id);
		
		return Msg.success().add("inspection", inspection);
		
	}
	
	/**
	 * ����Ƿ����
	 * @param InspectionName
	 * @return
	 */
	@RequestMapping("/checkInspection")
	@ResponseBody
	public Msg checkInspection(@RequestParam("person") String inspectionName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		/*String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,8})";
		if (!inspectionName.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-8λ���Ļ���6-16λӢ�����ֵ����");
		}
		return Msg.success();*/
		
		//���ݿ��û����ظ�У��
		boolean b = inspectionService.checkInspection(inspectionName);
		if(!b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "������");
		}
		
	}
	
	/**
	 * Ѳ�鱣��
	 * 1.֧��JSR303У��
	 * ���� hibernate validator
	 * @param inspection
	 * @return
	 */
	
	//����Ѳ��
	@RequestMapping(value="/inspection", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveInspection(@Valid Inspection inspection, BindingResult result) {
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
			inspectionService.saveInspection(inspection);
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
	@RequestMapping("/inspections")
	@ResponseBody
	public Msg getInspectionsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<Inspection> inspections = inspectionService.getAll();
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(inspections, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/inspections/{itime}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getInspections(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("itime") Date itime) {
		
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<Inspection> inspections = inspectionService.getInspectionByName(itime);
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(inspections, 5);
		return Msg.success().add("pageInfo", page);
	}

}
