package com.chinasoft.po;

import lombok.Data;

import java.util.Date;

@Data
public class Allocate {

    private long id;
    private long groupId;
    private long expertId;
    private long allocateUserId;
    private Date allocateTime;
}
