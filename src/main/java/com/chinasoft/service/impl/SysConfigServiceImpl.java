package com.chinasoft.service.impl;

import com.chinasoft.dao.SysConfigDao;
import com.chinasoft.entity.sysConfig;
import com.chinasoft.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;
    @Override
    public List<sysConfig> querySysConfig() {
        return sysConfigDao.querySysConfig();
    }

    @Override
    public int updateSysConfig(String configName, String configValue) {
        return sysConfigDao.updateSysConfig(configName, configValue);
    }
}
