package com.chinasoft.param;

import lombok.Data;

/**
 * 查询申请评审任务时参数封装类
 *
 */
@Data
public class UserListParam {
	private String name;
	private String workNumber;
	private String username;
	private Long roleId;
	//分页
	private int offset;
	private int limit;
}
