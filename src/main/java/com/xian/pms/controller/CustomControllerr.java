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

//处理增删改查
@Controller
public class CustomControllerr {

	@Autowired
	CustomService customService;
	
	/**
	 * 单个 批量删除  二合一
	 * 批量删除  1-2-3
	 * 单个删除  1
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/custom/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteCustom(@PathVariable("ids") String ids) {
		//批量删除
		if (ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String [] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string)); 
			}
			customService.deleteBatch(del_ids);
		//单个删除
		}else {
			int id = Integer.parseInt(ids);
			customService.deleteCustom(id);
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
	 * 根据id查询业主
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
	 * 检察用户名是否可用
	 * @param adminName
	 * @return
	 */
	@RequestMapping("/checkCustom")
	@ResponseBody
	public Msg checkCustom(@RequestParam("name") String customName) {
		//先判断用户名是否是合法的表达式
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!customName.matches(regx)) {
			return Msg.fail().add("va_msg", "可以是2-5位中文或者6-16位英文数字的组合");
		}
		
		//数据库用户名重复校验
		boolean b = customService.checkCustom(customName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名不可用");
		}
		
	}
	
	/**
	 * 员工保存
	 * 1.支持JSR303校验
	 * 导入 hibernate validator
	 * @param CustomAccount
	 * @return
	 */
	
	//新增管理员
	@RequestMapping(value="/custom", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveCustom(@Valid CustomAccount customAccount, BindingResult result) {
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
			customService.saveCustom(customAccount);
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
	@RequestMapping("/customs")
	@ResponseBody
	public Msg getCuustomWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 引入pageHelper分页插件
		// 在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// 紧跟的查询，就是一个分页查询
		List<CustomAccount> custom = customService.getAll();
		// 使用pageInfo包装查询结果，将pageInfo交给页面
		// 封装了详细的分页信息，包括查询出来的数据。连续显示的页数
		PageInfo page = new PageInfo(custom, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value="/customs/{name}", method=RequestMethod.GET)
	@ResponseBody
	public Msg getCustoms(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model, @PathVariable("name") String name) {
		
		// 引入pageHelper分页插件
		// 在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// 紧跟的查询，就是一个分页查询
		List<CustomAccount> custom = customService.getCustomByName(name);
		// 使用pageInfo包装查询结果，将pageInfo交给页面
		// 封装了详细的分页信息，包括查询出来的数据。连续显示的页数
		PageInfo page = new PageInfo(custom, 5);
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 业主登录
	 * @param custom
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/checkLoginc")
	@ResponseBody
	public Msg checkLoginc(@RequestParam("name") String adminName) {
		//先判断用户名是否是合法的表达式
		/*String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if (!adminName.matches(regx)) {
			return Msg.fail().add("va_msg", "可以是2-5位中文或者6-16位英文数字的组合");
		}*/
		
		//数据库用户名是否存在校验
		boolean b = customService.checkCustom(adminName);
		if(!b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名不存在");
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
		
		// 引入pageHelper分页插件
		// 在查询之前只需要调用,传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// 紧跟的查询，就是一个分页查询
		List<CustomAccount> custom = customService.getCustomById(id);
		// 使用pageInfo包装查询结果，将pageInfo交给页面
		// 封装了详细的分页信息，包括查询出来的数据。连续显示的页数
		PageInfo page = new PageInfo(custom, 5);
		return Msg.success().add("pageInfo", page);
	}
	
}
