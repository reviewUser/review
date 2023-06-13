package com.chinasoft.po;

import lombok.Data;

import java.util.Date;

/**
 * 申请评审任务持久类
 * 
 */
@Data
public class Project {

	//评审任务评审状态 ：(待分组,待分配，待评审，评审中，评审完成)
	public static final String PROJECT_REVIEW_STATUS_WAIT_ALLOCATE = "WAIT_ALLOCATE";
    public static final String PROJECT_REVIEW_STATUS_WAIT_REVIEW = "WAIT_REVIEW";
    public static final String PROJECT_REVIEW_STATUS_IN_REVIEW = "IN_REVIEW";
    public static final String PROJECT_REVIEW_STATUS_REVIEW_DONE = "REVIEW_DONE" ;

	private long id;
	private String projectName; // 评审任务名
	private String description; // 评审任务简介
	private String status; // 评审任务评审状态
	private String fileSavePath; // 评审任务文件保存路径
	private long createUserId; // 申请者id
	private String createUserName; //因layui的限制所创建，用于显示layui表格的限制
	private Date createTime; // 申请创建时间
	private Date lastUpdateTime; // 最后更改时间
	private SysUser user; // 评审任务申请者持久类
	private long groupId;

	private long expertId;
	private int reviewCount;
}
