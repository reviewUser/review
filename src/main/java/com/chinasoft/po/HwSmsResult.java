package com.chinasoft.po;

import lombok.Data;

@Data
public class HwSmsResult {

    private String originTo;

    private String createTime;

    private String from;

    private String smsMsgId;

    private String status;

}