package com.chinasoft.param;

import lombok.Data;

import java.util.Date;

@Data
public class CustomParam {
    private Date startTime;
    private Date endTime;
    //分页
    private int offset;
    private int limit;
}
