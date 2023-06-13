package com.chinasoft.entity;

import lombok.Data;

@Data
public class CheckReviewStatus {
    private long id;
    private long review;
    private String phone;
    private String status;
    private String repeats;
}
