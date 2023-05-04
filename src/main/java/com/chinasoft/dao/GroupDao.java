package com.chinasoft.dao;

import com.chinasoft.param.GroupListParam;
import com.chinasoft.po.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GroupDao {

	/**
	 * 添加评审任务分组
	 * @param group
	 */
	void insert(Group group);
	
	/**
	 * 得到总记录数
	 * @param param
	 * @return
	 */
	int getTotalCount(GroupListParam param);
	
	/**
	 * 查询分组
	 * @param param
	 * @return
	 */
	List<Group> listGroup(GroupListParam param);
	
	/**
	 * 修改领域标签信息
	 * 
	 */
	int update(Group group);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Group getById(Long id);
	
}
