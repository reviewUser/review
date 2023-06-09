package com.chinasoft.po;

import lombok.Data;

import java.util.Date;

/**
 * 领域标签持久类
 */
@Data
public class Field {

    private long id;
    private String fieldName; // 领域名
    private long createUserId; // 创建人id
    private String createUserName; //因layui的限制所创建，用于显示layui表格的限制
    private Date createTime; // 创建时间
    private Date lastUpdateTime; // 创建时间
    private SysUser user; // 评审任务申请者持久类
}
