package com.xian.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转
 * @author starn
 *
 */


@Controller
public class ForwardController {

	// 跳转
	@RequestMapping(value = "/house", method = RequestMethod.GET)
	public String forwrodHouse() {
		return "house";
	}

	// 跳转
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String forwrodAdmin() {
		return "admin";
	}

	// 跳转
	@RequestMapping(value = "/custom", method = RequestMethod.GET)
	public String forwrodCustom() {
		return "custom";
	}
	
	// 跳转
	@RequestMapping(value = "/maintain", method = RequestMethod.GET)
	public String forwrodMaintain() {
		return "maintain";
	}
	
	// 跳转
	@RequestMapping(value = "/inspection", method = RequestMethod.GET)
	public String forwrodInspection() {
		return "inspection";
	}
	
	// 跳转
	@RequestMapping(value = "/sgc", method = RequestMethod.GET)
	public String forwrodSgc() {
		return "sgc";
	}
	
	// 跳转
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String forwrodNotice() {
		return "notice";
	}
	
	// 跳转
	@RequestMapping(value = "/system", method = RequestMethod.GET)
	public String forwrodSystem() {
		return "system";
	}
	
	// 跳转
	@RequestMapping(value = "/cmsgs", method = RequestMethod.GET)
	public String forwrodCmsgs() {
		return "cmsgs";
	}
	
	// 跳转
	@RequestMapping(value = "/csystem", method = RequestMethod.GET)
	public String forwrodCsystem() {
		return "csystem";
	}
	// 跳转
	@RequestMapping(value = "/cmsg", method = RequestMethod.GET)
	public String forwrodCmsg() {
		return "cmsg";
	}
	// 跳转
	@RequestMapping(value = "/cnotice", method = RequestMethod.GET)
	public String forwrodCnotice() {
		return "cnotice";
	}
	
	
}
