package com.chinasoft.po;

import lombok.Data;

import java.util.Date;
/**
 * 评审任务分组持久类
 *
 */
@Data
public class Group {
	
	public static final String PROJECT_GROUP_STATUS_WAIT_ALLOCATE = "WAIT_ALLOCATE";
	public static final String PROJECT_GROUP_STATUS_WAIT_ADD_PROJECT = "WAIT_ADD_PROJECT";
	public static final String PROJECT_GROUP_STATUS_ALLOCATE_DONE = "ALLOCATE_DONE";
	
	private long id;
	private String groupName; // 分组名
	private String status; // 分组名
	private long createUserId; // 创建人id
	private String createUserName; //因layui的限制所创建，用于显示layui表格的限制
	private Date createTime; // 创建时间
	private Date lastUpdateTime; // 创建时间
	private SysUser user; // 评审任务申请者持久类
}
