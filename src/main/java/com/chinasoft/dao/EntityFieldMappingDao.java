package com.chinasoft.dao;

import com.chinasoft.po.EntityField;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface EntityFieldMappingDao {
	/**
	 * 插入关联记录
	 * @param entityField
	 */
	public int insert(EntityField entityField);

	/**
	 * 根据projectId查询
	 * @param id
	 * @return
	 */
	public List<EntityField> listByEntityId(@Param("id")Long id, @Param("entityType")String entityType);
}
