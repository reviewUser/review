package com.chinasoft.po;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SmsParam {
    private String reviewName;

    private String reviewRemark;

    private String reviewUser;

    private Date reviewDate;

    private Date reviewStartDate;

    private Date reviewEndDate;

    private String reviewField;

    private String hour;
}
