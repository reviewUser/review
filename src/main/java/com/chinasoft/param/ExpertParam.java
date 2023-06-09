package com.chinasoft.param;

import lombok.Data;

/**
 * 查询申请评审任务时参数封装类
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
    private String unMeeting;
    private String address;
    private String workUnit;
    //分页
    //分页
    private int pageNum;
    private int pageSize;
}
