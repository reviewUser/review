package com.chinasoft.po;

import lombok.Data;

@Data
public class CheckReview {
    private long id;
    private long review;
    private String phone;
    private String status;
    private String repeats;
}
