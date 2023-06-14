package com.chinasoft.dao;

import com.chinasoft.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    SysUser login(SysUser sysUser);
}
