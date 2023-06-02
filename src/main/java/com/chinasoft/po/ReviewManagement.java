package com.chinasoft.po;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewManagement {
    @ExcelProperty("主键")
    private long id;
    @ExcelProperty("评审任务名")
    private String reviewName;
    @ExcelProperty("评审内容概要")
    private String reviewRemark;
    @ExcelProperty("评审发起人")
    private String reviewUser;
    @ExcelProperty("评审计划日期")
    private Date reviewDate;
    @ExcelProperty("评审开始时间")
    private Date reviewStartDate;
    @ExcelProperty("评审结束时间")
    private Date reviewEndDate;
    @ExcelProperty("评审所属专业领域")
    private String reviewField;
    @ExcelProperty("所需专家数量")
    private String reviewExperts;
    @ExcelProperty("评审状态")
    private String reviewStatus;
    @ExcelProperty("项目资金来源")
    private String fundSource;
    @ExcelProperty("会议地点")
    private String address;
}
