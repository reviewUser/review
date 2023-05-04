package com.chinasoft.param;

import java.util.List;
/**
 * 查询申请评审任务时参数封装类
 * @author 王鹏
 *
 */
public class ProjectListParam  extends CustomParam{

	private String projectName;
	private String status;
	private List<Long> createUserIds;
	private List<Long> groupIds;
	private long expertId;
	private Long groupId;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Long> getCreateUserIds() {
		return createUserIds;
	}

	public void setCreateUserIds(List<Long> createUserIds) {
		this.createUserIds = createUserIds;
	}

	public List<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<Long> groupIds) {
		this.groupIds = groupIds;
	}

	public long getExpertId() {
		return expertId;
	}

	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	
}