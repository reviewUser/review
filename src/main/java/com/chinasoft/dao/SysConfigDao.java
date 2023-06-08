package com.chinasoft.dao;

import com.chinasoft.entity.sysConfig;
import com.chinasoft.po.Allocate;
import com.chinasoft.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SysConfigDao {
    String getConfigValue(String configValue);

    List<sysConfig> querySysConfig();

    int updateSysConfig(String configName, String configValue);
}
