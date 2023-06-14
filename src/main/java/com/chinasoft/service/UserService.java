package com.chinasoft.service;

import com.chinasoft.entity.SysUser;

/**
 * 系统用户业务层接口
 */
public interface UserService {

    SysUser login(SysUser sysUser);
}
