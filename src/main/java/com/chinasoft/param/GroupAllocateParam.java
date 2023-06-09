package com.chinasoft.param;


import com.chinasoft.po.SysUser;
import lombok.Data;

@Data
public class GroupAllocateParam {

    private long groupId;
    private SysUser user;
    private long expertId;
}
