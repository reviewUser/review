package com.chinasoft.controller;

import com.chinasoft.entity.sysConfig;
import com.chinasoft.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping()
    public List<sysConfig> querySysConfig() {
        return sysConfigService.querySysConfig();
    }

    @PutMapping()
    public int updateSysConfig(String configName, String configValue){
        return sysConfigService.updateSysConfig(configName, configValue);
    }

}
