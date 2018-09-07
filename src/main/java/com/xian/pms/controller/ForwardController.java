package com.xian.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ҳ����ת
 * @author starn
 *
 */


@Controller
public class ForwardController {

	// ��ת
	@RequestMapping(value = "/house", method = RequestMethod.GET)
	public String forwrodHouse() {
		return "house";
	}

	// ��ת
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String forwrodAdmin() {
		return "admin";
	}

	// ��ת
	@RequestMapping(value = "/custom", method = RequestMethod.GET)
	public String forwrodCustom() {
		return "custom";
	}
	
	// ��ת
	@RequestMapping(value = "/maintain", method = RequestMethod.GET)
	public String forwrodMaintain() {
		return "maintain";
	}
	
	// ��ת
	@RequestMapping(value = "/inspection", method = RequestMethod.GET)
	public String forwrodInspection() {
		return "inspection";
	}
	
	// ��ת
	@RequestMapping(value = "/sgc", method = RequestMethod.GET)
	public String forwrodSgc() {
		return "sgc";
	}
	
	// ��ת
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String forwrodNotice() {
		return "notice";
	}
	
	// ��ת
	@RequestMapping(value = "/system", method = RequestMethod.GET)
	public String forwrodSystem() {
		return "system";
	}
	
	// ��ת
	@RequestMapping(value = "/cmsgs", method = RequestMethod.GET)
	public String forwrodCmsgs() {
		return "cmsgs";
	}
	
	// ��ת
	@RequestMapping(value = "/csystem", method = RequestMethod.GET)
	public String forwrodCsystem() {
		return "csystem";
	}
	// ��ת
	@RequestMapping(value = "/cmsg", method = RequestMethod.GET)
	public String forwrodCmsg() {
		return "cmsg";
	}
	// ��ת
	@RequestMapping(value = "/cnotice", method = RequestMethod.GET)
	public String forwrodCnotice() {
		return "cnotice";
	}
	
	
}
