package com.chinasoft.entity;

import lombok.Data;

/**
 * 系统用户
 */
@Data
public class SysUser {

    private long id;
    private String username; // 姓名
    private String password; // 密码
    private String sex; // 男，女
    private String mail; // 邮箱
    private String phone; // 手机号
    private String title; // 若用户为专家，则表示职称，默认为空
    private long roleId; //用户角色id
}
