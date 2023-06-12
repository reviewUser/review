package com.chinasoft.controller;

import com.alibaba.druid.util.StringUtils;
import com.chinasoft.exception.CustomException;
import com.chinasoft.param.FieldsListParam;
import com.chinasoft.po.SysUser;
import com.chinasoft.utils.Result;
import com.chinasoft.utils.UUIDUtils;
import com.chinasoft.po.Field;
import com.chinasoft.service.FieldService;
import com.chinasoft.utils.CustomDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/field")
public class FieldController {

	@Autowired
	private FieldService fieldService;

	/**
	 * 领域标签管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fieldPage")
	public String fieldPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		return "system/fieldManage/fieldManage";
	}
	
	/**
	 * 添加领域标签管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addFieldPage")
	public String addFieldPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		session.setAttribute("token", UUIDUtils.generateUUIDString());
		return "system/fieldManage/addField";
	}
	
	/**
	 * 查询领域标签记录接口，以json的形式返回
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fields.json")
	public Map<String, Object> getFieldsJson(HttpSession session, HttpServletRequest request) {
		String fieldName = request.getParameter("fieldName");
		String createTime = request.getParameter("createTime");
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = (Integer.valueOf(request.getParameter("page")) - 1) * limit;
		// 生成查询参数
		FieldsListParam param = new FieldsListParam();
		if (!StringUtils.isEmpty(fieldName))
			param.setFieldName(fieldName.trim());
		// 设置正确的日期格式
		if (!StringUtils.isEmpty(createTime))
			CustomDateUtils.setTimeRange(param, createTime);
		param.setOffset(offset);
		param.setLimit(limit);

		int totalCount = fieldService.getTotalCount(param);
		List<Field> fields = fieldService.listField(param);
		// 因为layui数据表格的限制，故将数据转成表格需要的格式
		formatFiled(fields);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", totalCount);
		map.put("msg", "");
		map.put("data", fields);
		return map;
	}

	/**
	 * 因为layui数据表格的限制，故将数据转成表格需要的格式
	 * @param fields
	 */
	private void formatFiled(List<Field> fields) {
		for (Field field : fields) {
			field.setCreateUserName(field.getUser().getUsername());
		}
	}

	/**
	 * 修改领域标签信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Result<String> update(HttpSession session, Field f) throws Exception {
		SysUser userSession = (SysUser) session.getAttribute("user");
		if (userSession == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		Result<String> result = new Result<String>();
		Field field = fieldService.getById(f.getId());
		if (field == null) {
			result.setData("该领域标签不存在，请刷新页面重新查看");
			return result;
		}
		field.setFieldName(f.getFieldName());
		field.setLastUpdateTime(new Date());
		int rows = fieldService.update(field);
		if (rows > 0) { 
			result.setData("修改成功");
		} else {
			result.setData("修改失败");
		}
		return result;
	}
	
	/**
	 * 删除领域信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public void delete(HttpSession session, Long id) {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		// 删除领域
		fieldService.deleteById(id);
		
	}	
	
	/**
	 * 添加领域
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addField")
	public String addField(HttpSession session, Model model, Field field, String token) throws Exception {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		if (session.getAttribute("token") == null) {
			model.addAttribute("message", "请勿重复提交表单");
			return "system/fieldManage/fieldManage";
		}
		if (session.getAttribute("token").equals(token)) {
			fieldService.insert(field,user);
			model.addAttribute("message", "添加成功");
			session.removeAttribute("token");
		}
		return "system/fieldManage/fieldManage";
	}
	
	/**
	 * 为专家添加擅长领域页面
	 * @param session
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/allocateFieldPage")
	public String allocateFieldPage(HttpSession session, Model model, Long id) throws Exception {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		session.setAttribute("expertId", id);
		return "system/userManage/allocateField";
	}
}
