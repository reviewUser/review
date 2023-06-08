package com.chinasoft.controller;

import com.chinasoft.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;
}
