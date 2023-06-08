package com.chinasoft.service;

import java.util.HashMap;

public interface SysConfigService{

    HashMap<String, String> querySysConfig();

    int updateSysConfig(String configName, String configValue);
}
