package com.chinasoft.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_config")
public class SysConfig {
    private String configName;
    private String configValue;
}
