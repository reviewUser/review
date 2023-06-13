package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/project")
public class ProjectController {
	/**
	 * 密码修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/allocateManagePage")
	public String allocateManagePage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}

		return "system/allocateManage/allocateManage";
	}
	
	/**
	 * 评审任务管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managePage")
	public String managePage(HttpSession session, HttpServletRequest request) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		return "system/projectManage/manage";
	}
}
