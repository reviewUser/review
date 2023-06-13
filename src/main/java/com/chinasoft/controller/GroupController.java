package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/group")
public class GroupController {

	/**
	 * 分组管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/groupManagePage")
	public String groupManagePage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		return "system/groupManage/groupManage";
	}
}
