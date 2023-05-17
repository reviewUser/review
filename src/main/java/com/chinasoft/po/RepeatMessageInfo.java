package com.chinasoft.po;

import lombok.Data;

import java.util.Date;

@Data
public class RepeatMessageInfo {
    private long id;
    private String phone;
    private Date time;
    private String repeats;
    private long review;
}
