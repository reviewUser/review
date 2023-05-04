package com.chinasoft.param;

import lombok.Data;

/**
 * 查询申请评审任务时参数封装类
 * @author 王鹏
 *
 */
@Data
public class ReviewParam {
	private String reviewName;
	private String reviewUser;
	private String reviewField;
	private String reviewStatus;
	//分页
	private int pageNum;
	private int pageSize;
}
