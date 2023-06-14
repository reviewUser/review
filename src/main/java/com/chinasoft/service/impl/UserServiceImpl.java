package com.chinasoft.service.impl;

import com.chinasoft.dao.UserDao;
import com.chinasoft.entity.SysUser;
import com.chinasoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户业务层接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public SysUser login(SysUser sysUser) {
        if (sysUser != null) {
            SysUser user = userDao.login(sysUser);
            return user;
        }
        return null;
    }
}
