package com.chinasoft.param;

import lombok.Data;

import java.util.List;

/**
 * 查询申请评审任务时参数封装类
 */
@Data
public class ProjectListParam extends CustomParam {

    private String projectName;
    private String status;
    private List<Long> createUserIds;
    private List<Long> groupIds;
    private long expertId;
    private Long groupId;
}