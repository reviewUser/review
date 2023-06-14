package com.chinasoft.controller;

import com.chinasoft.entity.SysConfig;
import com.chinasoft.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 配置管理页面
 */
@RestController
@RequestMapping("/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 查询
     * @return
     */
    @GetMapping()
    public List<SysConfig> querySysConfig() {
        return sysConfigService.querySysConfig();
    }

    /**
     * 修改
     * @param configName
     * @param configValue
     * @return
     */
    @PutMapping()
    public int updateSysConfig(String configName, String configValue){
        return sysConfigService.updateSysConfig(configName, configValue);
    }

}
