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
import com.xian.pms.bean.House;
import com.xian.pms.bean.Msg;
import com.xian.pms.service.HouseService;

//������ɾ�Ĳ�
@Controller
public class HouseControllerr {

	@Autowired
	HouseService houseService;
	
	/**
	 * ���� ����ɾ��  ����һ
	 * ����ɾ��  1-2-3
	 * ����ɾ��  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/house/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteHouse(@PathVariable("ids") String ids) {
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			houseService.deleteBatch(del_ids);
		//����ɾ��
		}else {
			int id = Integer.parseInt(ids);
			houseService.deleteHouse(id);
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
	@RequestMapping( value="/house/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveHouse(House house) {
		houseService.updateHouse(house);
		return Msg.success();
		
	}
	
	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value="/house/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getAdmin(@PathVariable("id") Integer id) {
		
		House house = houseService.getHouse(id);
		
		return Msg.success().add("house", house);
		
	}
	
	/**
	 * ����Ƿ����
	 * @param adminName
	 * @return
	 */
	@RequestMapping("/checkHouse")
	@ResponseBody
	public Msg checkHouse(@RequestParam("num") String num) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		String regx = "(^[a-zA-Z0-9_-]{2,6}$)";
		if (!num.matches(regx)) {
			return Msg.fail().add("va_msg", "������2-6Ӣ�����ֵ����");
		}
		
		//���ݿ��û����ظ�У��
		boolean b = houseService.checkHouse(num);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "���ƺ��Ѵ���");
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
	@RequestMapping(value="/house", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveHouse(@Valid House house, BindingResult result) {
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
			houseService.saveHouse(house);
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
	@RequestMapping("/houses")
	@ResponseBody
	public Msg getHousesWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<House> houses = houseService.getAll();
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(houses, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/houses/{num}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getHouses(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("num") String num) {
		
		// ����pageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ����,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// �����Ĳ�ѯ������һ����ҳ��ѯ
		List<House> houses = houseService.getHousesByNum(num);
		// ʹ��pageInfo��װ��ѯ�������pageInfo����ҳ��
		// ��װ����ϸ�ķ�ҳ��Ϣ��������ѯ���������ݡ�������ʾ��ҳ��
		PageInfo page = new PageInfo(houses, 5);
		return Msg.success().add("pageInfo", page);
	}
	
	//��ѯ����
	@RequestMapping(value="/hous/{oid}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getHouse(@PathVariable("oid") String oid) {
		
		List<House> house = houseService.getHou(oid);
		
		return Msg.success().add("house", house);
		
	}
	
}
