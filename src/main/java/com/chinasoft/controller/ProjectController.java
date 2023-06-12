package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import com.chinasoft.param.GenerateApplyParam;
import com.chinasoft.po.Project;
import com.chinasoft.service.AllocateService;
import com.chinasoft.service.GroupService;
import com.chinasoft.service.ProjectService;
import com.chinasoft.service.UserService;
import com.chinasoft.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private AllocateService allocateService;
	
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

	/**
	 * 处理上传请求
	 * 
	 * @param model
	 * @param file
	 * @param session
	 * @param project
	 * @param tagsinput
	 * @param token 防止表单重复重复
	 *            领域标签用逗号隔开
	 * @return
	 */
	@RequestMapping("/upload")
	public String upload(Model model, MultipartFile file, HttpSession session, Project project, String tagsinput, String token) {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		if (session.getAttribute("token") == null) {
			model.addAttribute("message", "请勿重复提交表单");
			return "applicant/upload";
		}
		if (session.getAttribute("token").equals(token)) {
			// 生成申请记录
			GenerateApplyParam param = new GenerateApplyParam();
			param.setModel(model);
			param.setFile(file);
			param.setProject(project);
			param.setSysUser(user);
			param.setTagsinput(tagsinput);
			projectService.generateApply(param);
			session.removeAttribute("token");
		}
		return "applicant/upload";
	}
}
