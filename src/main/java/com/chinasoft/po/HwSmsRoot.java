package com.chinasoft.po;

import lombok.Data;

import java.util.List;

@Data
public class HwSmsRoot {

    private List<HwSmsResult> result;

    private String code;

    private String description;

}