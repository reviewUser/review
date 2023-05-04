package com.chinasoft.po;

import java.util.Date;

/**
 * 申请评审任务持久类
 * 
 * @author 王鹏
 *
 */
public class Project {

	//评审任务评审状态 ：(待分组,待分配，待评审，评审中，评审完成)
	//public static final String PROJECT_REVIEW_STATUS_WAIT_GROUP = "WAIT_GROUP";
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

	public long getExpertId() {
		return expertId;
	}

	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileSavePath() {
		return fileSavePath;
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
}
