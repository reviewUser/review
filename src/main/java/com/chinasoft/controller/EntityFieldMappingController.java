package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import com.chinasoft.po.EntityField;
import com.chinasoft.po.Field;
import com.chinasoft.po.SysUser;
import com.chinasoft.service.EntityFieldMappingService;
import com.chinasoft.service.FieldService;
import com.chinasoft.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/entityField")
public class EntityFieldMappingController {

	@Autowired
	private FieldService fieldService;

	@Autowired
	private EntityFieldMappingService entityFieldMappingService;
	
	/**
	 * 查找相关领域
	 * @param session
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/relateField")
	public Result<String> getRelateFields(HttpSession session,Long id,String entityType){
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/");
		}
		Result<String> result = new Result<String>();
		List<EntityField> entityFields = entityFieldMappingService.listByEntityId(id,entityType);
		List<Long> fieldIds = new ArrayList<Long>();
		if(CollectionUtils.isEmpty(entityFields)){
			result.setData("查询不到相关领域");
			return result;
		}
		for (EntityField entityField : entityFields) {
			fieldIds.add(entityField.getFieldId());
		}
		List<Field> fields = null;
		if(!CollectionUtils.isEmpty(fieldIds)){
			fields = fieldService.listByIds(fieldIds);
		}
		StringBuffer buffer = new StringBuffer();
		for (Field field : fields) {
			buffer.append(field.getFieldName()).append(",");
		}
		String data = buffer.toString().substring(0,buffer.length()-1);
		result.setData(data);
		return result;
	}
}
