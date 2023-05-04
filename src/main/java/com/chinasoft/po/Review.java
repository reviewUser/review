package com.chinasoft.po;

import java.util.Date;

/**
 * 评审任务评审持久类
 * 
 * @author 王鹏
 *
 */
public class Review {

	public static final String REVIEW_RESULT_PASS = "PASS";
	public static final String REVIEW_RESULT_REJECT = "REJECT";
	
	private long id;
	private long applicantId; //评审任务申请人
	private long projectId; // 
	private long expertId;
	private String expertName;
	private String opinion; // 专家意见
	private String grade; // 评审成绩
	private Date reviewTime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public long getApplicantId() {
		return applicantId;
	}

	public long getProjectId() {
		return projectId;
	}

	public long getExpertId() {
		return expertId;
	}

	public String getOpinion() {
		return opinion;
	}

	public String getGrade() {
		return grade;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	
}
