package com.chinasoft.service.impl;

import com.chinasoft.dao.UserDao;
import com.chinasoft.param.UserListParam;
import com.chinasoft.po.SysUser;
import com.chinasoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户业务层接口实现
 * 
 * @author 王鹏
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 注册
	 * 
	 * @param applicant
	 */
	@Override
	public void register(SysUser sysUser) {
		if (sysUser != null)
			userDao.register(sysUser);
	}

	/**
	 * 评审任务申请者登陆
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public SysUser login(SysUser sysUser) {
		if (sysUser != null) {
			SysUser user = userDao.login(sysUser);
			return user;
		}
		return null;
	}

	/**
	 * 修改个人信息
	 * 
	 * @param user
	 */
	@Override
	public int update(SysUser user) {
		if (user != null) {
			return userDao.update(user);
		}
		return 0;
	}

	/**
	 * 根据id查找用户
	 * @param user
	 * @return
	 */
	@Override
	public SysUser getUserById(Long id) {
		if (id != null) {
			SysUser user = userDao.getUserById(id);
			if (user != null)
				return user;
		}
		return null;
	}

	/**
	 * 通过name模糊搜索用户
	 * @param createUserName
	 * @return
	 */
	@Override
	public List<Long> getUserByUserName(String createUserName) {
		if (createUserName == null){
			return null;
		}
		List<SysUser> users = userDao.getUserByUserName(createUserName);
		List<Long> userIds = new ArrayList<Long>();
		for (SysUser user : users) {
			userIds.add(user.getId());
		}
		return userIds;
	}

	/**
	 * 通过name和角色搜索用户
	 * @param username
	 * @param roleId
	 * @return
	 */
	@Override
	public List<SysUser> listUsersByNameAndRole(UserListParam param) {
		List<SysUser> users = null;
		if (param.getRoleId() == null && StringUtils.isEmpty(param.getUsername())){
			return null;
		}
		users = userDao.listUsersByNameAndRole(param);
		return users;
	}

	@Override
	public int getTotalCount(UserListParam param) {
		if (param != null){
			return userDao.getTotalCount(param);
		}
		return 0;
	}

	/**
	 * 根据id查询专家
	 * @return
	 */
	@Override
	public List<SysUser> listExpertByIds(List<Long> idList) {
		List<SysUser> experts = null;
		experts = userDao.listExpertByIds(idList);
		return experts;
	}
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		if (id != null ){
			userDao.deleteById(id);
		}
	}
}
