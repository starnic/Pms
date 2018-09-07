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

//处理增删改查
@Controller
public class InspectionControllerr {

	@Autowired
	InspectionService inspectionService;
	
	/**
	 * 单个 批量删除  二合一
	 * 批量删除  1-2-3
	 * 单个删除  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/inspection/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteInspection(@PathVariable("ids") String ids) {
		//批量删除
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			inspectionService.deleteBatch(del_ids);
		//单个删除
		}else {
			int id = Integer.parseInt(ids);
			inspectionService.deleteInspection(id);
		}
		return Msg.success();
		
	}
	
	
	/**
	 * 更新方法
	 *ajax不能直接发送put请求
	 * 
	 * 解决方案：
	 * 
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 配置上  HttpPutFormContentFilter 
	 * 作用，就是将请求体中的数据解析包装成一个map，request被重新包装
	 * request被重新包装，reqyest.getParameter()被重写，就会从自己封装的map中取数据
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
	 * 根据id查询管理员
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
	 * 检察是否可用
	 * @param InspectionName
	 * @return
	 */
	@RequestMapping("/checkInspection")
	@ResponseBody
	public Msg checkInspection(@RequestParam("person") String inspectionName) {
		//先判断用户名是否是合法的表达式
		/*String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,8})";
		if (!inspectionName.matches(regx)) {
			return Msg.fail().add("va_msg", "可以是2-8位中文或者6-16位英文数字的组合");
		}
		return Msg.success();*/
		
		//数据库用户名重复校验
		boolean b = inspectionService.checkInspection(inspectionName);
		if(!b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "不存在");
		}
		
	}
	
	/**
	 * 巡查保存
	 * 1.支持JSR303校验
	 * 导入 hibernate validator
	 * @param inspection
	 * @return
	 */
	
	//新增巡查
	@RequestMapping(value="/inspection", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveInspection(@Valid Inspection inspection, BindingResult result) {
		if(result.hasErrors()) {
			//校验失败返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			inspectionService.saveInspection(inspection);
			return Msg.success();
		}
		
	}
	
	/**
	 * 查询数据 分页查询
	 * 
	 * @return
	 * 
	 * 		导入jackson包
	 */
	@RequestMapping("/inspections")
	@ResponseBody
	public Msg getInspectionsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 引入pageHelper分页插件
		// 在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// 紧跟的查询，就是一个分页查询
		List<Inspection> inspections = inspectionService.getAll();
		// 使用pageInfo包装查询结果，将pageInfo交给页面
		// 封装了详细的分页信息，包括查询出来的数据。连续显示的页数
		PageInfo page = new PageInfo(inspections, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/inspections/{itime}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getInspections(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("itime") Date itime) {
		
		// 引入pageHelper分页插件
		// 在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// 紧跟的查询，就是一个分页查询
		List<Inspection> inspections = inspectionService.getInspectionByName(itime);
		// 使用pageInfo包装查询结果，将pageInfo交给页面
		// 封装了详细的分页信息，包括查询出来的数据。连续显示的页数
		PageInfo page = new PageInfo(inspections, 5);
		return Msg.success().add("pageInfo", page);
	}

}
