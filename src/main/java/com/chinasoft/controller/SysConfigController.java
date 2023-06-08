package com.chinasoft.controller;

import com.chinasoft.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping()
    public HashMap<String, String> querySysConfig() {
        return sysConfigService.querySysConfig();
    }

    @PutMapping()
    public int updateSysConfig(String configName, String configValue){
        return sysConfigService.updateSysConfig(configName, configValue);
    }

}
