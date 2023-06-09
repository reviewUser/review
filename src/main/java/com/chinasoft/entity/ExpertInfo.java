package com.chinasoft.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExpertInfo {
    @ExcelProperty("主键")
    private long id;

    @ExcelProperty("工号")
    private String workNumber;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("职级")
    private String Level;

    @ExcelProperty("技术等级")
    private String technologyLevel;

    @ExcelProperty("擅长领域")
    private String fieldName;

    @ExcelProperty("工作单位")
    private String workUnit;

    @ExcelProperty("所在区域")
    private String address;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("生日")
    private String birthday;

    @ExcelProperty("年龄")
    private long age;

    @ExcelProperty("参与评审积分")
    private long integral;

    @ExcelProperty("拒绝评审次数")
    private long refuseCount;

    @ExcelProperty("专家状态")
    private String expertStatus;

    @ExcelProperty("未参会次数")
    private long unMeeting;

    private long MeetingTimes;
}
