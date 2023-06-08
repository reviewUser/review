package com.chinasoft.controller;

import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.QueryDescVo;
import com.chinasoft.po.ReviewManagement;
import com.chinasoft.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping("/queryConfigInfo")
    public String queryReviewInfo(@RequestParam String configName) {
        sysConfigService.getConfigValue(configName);
 return null;
    }
}
