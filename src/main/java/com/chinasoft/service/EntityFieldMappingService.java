package com.chinasoft.service;

import com.chinasoft.po.EntityField;

import java.util.List;

/**
 * 用于评审任务申请、专家与领域相关联的业务层接口
 * @author 王鹏
 *
 */
public interface EntityFieldMappingService {

	/**
	 * 添加关联
	 * @param entityField
	 */
	int insert(EntityField entityField);

	/**
	 * 根据projectId查询
	 * @param id
	 * @return
	 */
	List<EntityField> listByEntityId(Long id,String entityType);

	
}
