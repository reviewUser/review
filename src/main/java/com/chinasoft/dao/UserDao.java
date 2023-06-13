package com.chinasoft.dao;

import com.chinasoft.param.UserListParam;
import com.chinasoft.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

	/**
	 * 注册
	 * 
	 * @param sysUser
	 */
	void register(SysUser sysUser);

	/**
	 * 用户登陆
	 * 
	 * @param sysUser
	 */
	SysUser login(SysUser sysUser);

	/**
	 * 修改个人信息
	 * @param user
	 */
	int update(SysUser user);

	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	SysUser getUserById(Long id);
	
	/**
	 * 通过name模糊搜索用户
	 * @param username
	 * @return
	 */
	List<SysUser> getUserByUserName(String username);

	/**
	 * 通过name和角色搜索用户
	 * @param param
	 * @return
	 */
	List<SysUser> listUsersByNameAndRole(UserListParam param);

	/**
	 * 得到总记录数
	 * @param param
	 */
	int getTotalCount(UserListParam param);
	
	/**
	 * 根据id查询专家
	 * @return
	 */
	List<SysUser> listExpertByIds(@Param("list")List<Long> idList);
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	void deleteById(Long id);
}
