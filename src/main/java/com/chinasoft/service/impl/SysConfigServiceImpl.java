package com.chinasoft.service.impl;

import com.chinasoft.dao.SysConfigDao;
import com.chinasoft.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;
    @Override
    public String getConfigValue(String configName) {
        return sysConfigDao.getConfigValue(configName);
    }
}
