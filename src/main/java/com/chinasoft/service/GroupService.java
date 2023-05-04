package com.chinasoft.service;

import com.chinasoft.param.GroupAllocateParam;
import com.chinasoft.param.GroupListParam;
import com.chinasoft.po.Group;
import com.chinasoft.po.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 评审任务分组业务层接口
 * @author 王鹏
 *
 */
public interface GroupService {

	/**
	 * 添加分组
	 * @param group
	 */
	void insert(Group group, SysUser user);

	
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
	 * 修改评审任务分组信息
	 * 
	 */
	int update(Group group);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Group getById(Long id);


	/**
	 * 分配专家
	 * @param param
	 */
	int allocate(GroupAllocateParam param);
	
	/**
	 * 根据id删除
	 * @param id
	 */
//	public void deleteById(Long id);
}
