package com.chinasoft.dao;

import com.chinasoft.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigDao {
    String getConfigValue(String configValue);

    List<SysConfig> querySysConfig();

    int updateSysConfig(String configName, String configValue);
}
