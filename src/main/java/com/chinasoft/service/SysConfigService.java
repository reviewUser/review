package com.chinasoft.service;

import com.chinasoft.entity.sysConfig;

import java.util.List;

public interface SysConfigService {

    List<sysConfig> querySysConfig();

    int updateSysConfig(String configName, String configValue);
}
