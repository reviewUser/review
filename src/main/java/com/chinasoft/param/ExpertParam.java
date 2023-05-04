package com.chinasoft.param;

import lombok.Data;

/**
 * 查询申请评审任务时参数封装类
 * @author 王鹏
 *
 */
@Data
public class ExpertParam {
	private String name;
	private String workNumber;
	private String level;
	private String technologyLevel;
	private String fieldName;
	private String phone;
	private String integral;
	private String refuseCount;
	private String expertStatus;

	//分页
	//分页
	private int pageNum;
	private int pageSize;
}
