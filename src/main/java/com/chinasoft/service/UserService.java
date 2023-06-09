package com.chinasoft.service;

import com.chinasoft.param.UserListParam;
import com.chinasoft.po.SysUser;

import java.util.List;

/**
 * 系统用户业务层接口
 */
public interface UserService {

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
     * @return
     */
    SysUser login(SysUser sysUser);

    /**
     * 修改个人信息
     *
     * @param user
     */
    int update(SysUser user);

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    SysUser getUserById(Long id);

    /**
     * 通过name模糊搜索用户
     *
     * @param createUserName
     * @return
     */
    List<Long> getUserByUserName(String createUserName);

    /**
     * 通过name和角色搜索用户
     *
     * @param param
     * @return
     */
    List<SysUser> listUsersByNameAndRole(UserListParam param);

    /**
     * 得到总记录数
     *
     * @param param
     */
    int getTotalCount(UserListParam param);

    /**
     * 根据id查询专家
     *
     * @return
     */
    List<SysUser> listExpertByIds(List<Long> idList);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void deleteById(Long id);
}
