package com.chinasoft.param;

import lombok.Data;

/**
 * 查询申请评审任务时参数封装类
 *
 */
@Data
public class ReviewParam {
	private String reviewName;
	private String reviewUser;
	private String reviewField;
	private String reviewStatus;
	private String address;
	private String sourceAddress;
	//分页
	private int pageNum;
	private int pageSize;
}
