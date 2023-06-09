package com.chinasoft.po;

import lombok.Data;

import java.util.Date;

/**
 * 评审任务评审持久类
 */
@Data
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
}
