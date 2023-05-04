package com.chinasoft.controller;

import com.alibaba.druid.util.StringUtils;
import com.chinasoft.exception.CustomException;
import com.chinasoft.param.GenerateApplyParam;
import com.chinasoft.param.ProjectListParam;
import com.chinasoft.po.Allocate;
import com.chinasoft.po.Project;
import com.chinasoft.service.AllocateService;
import com.chinasoft.service.GroupService;
import com.chinasoft.service.ProjectService;
import com.chinasoft.service.UserService;
import com.chinasoft.utils.Md5Utils;
import com.chinasoft.utils.Result;
import com.chinasoft.utils.UUIDUtils;
import com.chinasoft.po.Group;
import com.chinasoft.po.SysUser;
import com.chinasoft.utils.CustomDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
	 * 评审任务申请页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadPage")
	public String uploadPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		session.setAttribute("token", UUIDUtils.generateUUIDString());
		return "applicant/upload";
	}
	
	/**
	 * 用户管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userPage")
	public String userPage(HttpSession session, HttpServletRequest request) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		String role = request.getParameter("role");
		String path = null;
		switch (role) {
		case "1":
			path = "system/userManage/applicant";
			break;
		case "2":
			path = "system/userManage/expert";
			break;
		case "3":
			path = "system/userManage/system";
			break;
		default:
			break;
		}

		return path;
	}
	
	/**
	 * 添加用户页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUserPage")
	public String addUserPage(HttpSession session, Model model, String roleId) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		model.addAttribute("roleId", roleId);
		session.setAttribute("token", UUIDUtils.generateUUIDString());
		return "system/userManage/addUser";
	}
	
	/**
	 * 添加用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUser")
	public String addUser(HttpSession session, Model model, String role, SysUser user, String token) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		String path = null;
		switch (role) {
		case "1":
			path = "system/userManage/applicant";
			break;
		case "2":
			path = "system/userManage/expert";
			break;
		case "3":
			path = "system/userManage/system";
			break;
		default:
			break;
		}
		if (session.getAttribute("token") == null) {
			model.addAttribute("message", "请勿重复提交表单");
			return path;
		}
		if (session.getAttribute("token").equals(token)) {
			String md5Password = Md5Utils.md5(user.getPassword()); // 密码采用MD5加密
			user.setPassword(md5Password);
			userService.register(user);
			model.addAttribute("message", "添加成功");
			session.removeAttribute("token");
		}
		return path;
	}
	
	/**
	 * 评审任务分配管理页面
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
	 * 评审任务分配页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/allocatePage")
	public String allocatePage(Model model, HttpSession session,Long id) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		session.setAttribute("groupId", id);
		return "system/allocateManage/allocate";
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

	/**
	 * 删除评审任务申请记录
	 * @param session
	 * @param id
	 */
	@RequestMapping("/delete")
	public void delete(HttpSession session, Long id) {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		// 删除记录
		projectService.deleteById(id);
	}

	/**
	 * 更新申请记录接口
	 * 
	 * @param model
	 * @param session
	 * @param project
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Result<String> update(Model model, HttpSession session, Project project) {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		Result<String> result = new Result<String>();
		Project p = projectService.getProjectById(project.getId());
		if (p == null) {
			result.setData("该申请记录不存在，请刷新页面重新查看");
			return result;
		}
		p.setProjectName(project.getProjectName());
		p.setDescription(project.getDescription());
		// 更新申请记录
		p.setLastUpdateTime(new Date());
		int rows = projectService.update(p); // 返回影响的行数
		if (rows > 0) {
			result.setData("修改成功");
		} else {
			result.setData("修改失败");
		}
		return result;
	}

	/**
	 * 查询评审任务申请记录接口，以json的形式返回
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/projects.json")
	public Map<String, Object> getProjectsJson(HttpSession session, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser user = (SysUser) session.getAttribute("user");
		String projectName = request.getParameter("projectName");
		String status = request.getParameter("status");
		String createTime = request.getParameter("createTime");
		String isApplicant = request.getParameter("isApplicant");
		String isExpert = request.getParameter("isExpert");
		String groupId = request.getParameter("groupId");
		int limit = Integer.valueOf(request.getParameter("limit"));
		List<Long> createUserIds = null;
		int offset = (Integer.valueOf(request.getParameter("page")) - 1) * limit;
		// 生成查询参数
		ProjectListParam param = new ProjectListParam();
		if (!StringUtils.isEmpty(groupId))
			param.setGroupId(Long.parseLong(groupId.trim()));
		if (!StringUtils.isEmpty(projectName))
			param.setProjectName(projectName.trim());
		if (!StringUtils.isEmpty(status))
			param.setStatus(status.trim());
		if (!StringUtils.isEmpty(isApplicant)){
			createUserIds = new ArrayList<Long>();
			createUserIds.add(user.getId());
			param.setCreateUserIds(createUserIds);
		}
		if (!StringUtils.isEmpty(isExpert)){
			List<Allocate> allocates = allocateService.listByExpertId(user.getId());
			if (!CollectionUtils.isEmpty(allocates)) {
				List<Long> groupIds = new ArrayList<Long>();
				for (Allocate allocate : allocates) {
					groupIds.add(allocate.getGroupId());
				}
				param.setGroupIds(groupIds);
			}
		}
		String createUserName = request.getParameter("username");
		if (!StringUtils.isEmpty(createUserName)) {
			createUserIds = userService.getUserByUserName(createUserName.trim());
			if (CollectionUtils.isEmpty(createUserIds)){
				map.put("code", 0);
				map.put("count", 0);
				map.put("msg", "");
				map.put("data", new ArrayList<Project>());
				return map;
			}
			param.setCreateUserIds(createUserIds);
		}
		
		// 设置正确的日期格式
		if (!StringUtils.isEmpty(createTime))
			CustomDateUtils.setTimeRange(param, createTime);
		param.setOffset(offset);
		param.setLimit(limit);

		int totalCount = projectService.getTotalCount(param);
		List<Project> projects = projectService.listProject(param);
		// 因为layui数据表格的限制，故将数据转成表格需要的格式
		formatProject(projects);
		map.put("code", 0);
		map.put("count", totalCount);
		map.put("msg", "");
		map.put("data", projects);
		return map;
	}

	/**
	 * 删除分组中某个已添加评审任务
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delGroup")
	public void delGroup(HttpSession session,Long id,Long groupId) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		Project p = projectService.getProjectById(id);
		if (p == null) {
			throw new CustomException("该申请记录不存在，请刷新页面重新查看", "/group/groupProjectPage");
		}
		p.setGroupId(0);
		p.setStatus(Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE);
		p.setLastUpdateTime(new Date());
		projectService.update(p);
		
		List<Project> groupProjects = projectService.listByGroupId(groupId); 
		if (CollectionUtils.isEmpty(groupProjects)){
			Group group = groupService.getById(groupId);
			if (group == null) {
				throw new CustomException("该分组不存在，请刷新页面重新查看", "/group/groupManagePage");
			}
			group.setStatus(Group.PROJECT_GROUP_STATUS_WAIT_ADD_PROJECT);
			groupService.update(group);
		}
	}
	
	/**
	 * 因为layui数据表格的限制，故将数据转成表格需要的格式
	 * 
	 * @param projects
	 */
	private void formatProject(List<Project> projects) {
		for (Project project : projects) {
			project.setCreateUserName(project.getUser().getUsername());
//			if (Project.PROJECT_REVIEW_STATUS_WAIT_GROUP.equals(project.getStatus())) {
//				project.setStatus("待分组");
//			}
			if (Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE.equals(project.getStatus())) {
				project.setStatus("待分配");
			}
			if (Project.PROJECT_REVIEW_STATUS_WAIT_REVIEW.equals(project.getStatus())) {
				project.setStatus("待评审");
			}
			if (Project.PROJECT_REVIEW_STATUS_IN_REVIEW.equals(project.getStatus())) {
				project.setStatus("评审中");
			}
			if (Project.PROJECT_REVIEW_STATUS_REVIEW_DONE.equals(project.getStatus())) {
				project.setStatus("评审完成");
			}
		}
	}

}
