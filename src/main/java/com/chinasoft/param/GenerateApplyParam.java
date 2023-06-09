package com.chinasoft.param;

import com.chinasoft.po.Project;
import com.chinasoft.po.SysUser;
import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 * 评审任务申请人创建申请时所需参数，为了能够实现事务，即文件的上传、记录的生成、领域的标签保持在同一个事务里
 */
@Data
public class GenerateApplyParam {

    private Model model;
    private MultipartFile file;
    private Project project;
    private String tagsinput;
    private SysUser sysUser;
}
