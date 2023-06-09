package com.chinasoft.po;

import lombok.Data;

import java.util.Date;

/**
 * 系统角色表
 */

@Data
public class SysRole {

    private long id;
    private String roleName; //角色名
    private String roleNote; //角色说明
    private long createUserId; //创建人id
    private Date createTime; //创建时间
    private Date lastUpdateTime; //最后更新时间
}
