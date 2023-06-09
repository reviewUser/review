package com.chinasoft.service;

import com.chinasoft.entity.SysConfig;

import java.util.List;

public interface SysConfigService {

    List<SysConfig> querySysConfig();

    int updateSysConfig(String configName, String configValue);
}
