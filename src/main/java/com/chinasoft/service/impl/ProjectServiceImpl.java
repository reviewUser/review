package com.chinasoft.service.impl;

import com.chinasoft.dao.FieldDao;
import com.chinasoft.dao.GroupDao;
import com.chinasoft.dao.ProjectDao;
import com.chinasoft.param.GenerateApplyParam;
import com.chinasoft.param.ProjectListParam;
import com.chinasoft.po.Group;
import com.chinasoft.po.Project;
import com.chinasoft.service.FieldService;
import com.chinasoft.service.ProjectService;
import com.chinasoft.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static List<String> validSuffixs = null; // 有效文件类型集合

    static {
        validSuffixs = new ArrayList<String>();
        validSuffixs.add(".pdf");
        validSuffixs.add(".doc");
        validSuffixs.add(".docx");
    }
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private GroupDao groupDao;

    /**
     * 生成申请记录
     *
     * @param param 记录生成参数
     */
    @Transactional
    @Override
    public void generateApply(GenerateApplyParam param) {
        // 检查文件类型是否合法
        boolean isSuffixValid = checkFileSuffix(param.getFile().getOriginalFilename());
        if (!isSuffixValid) {
            param.getModel().addAttribute("message", "不支持的文件类型");
            return;
        }
        // 将文件保存
        String filePath = null;
        try {
            filePath = FileUtils.upload(param.getFile());
            param.getModel().addAttribute("message", "申请成功，请移至 个人中心->我的申请 查看");
        } catch (IOException e) {
            param.getModel().addAttribute("message", "上传失败，请重新上传！");
            e.printStackTrace();
        }
        // 生成评审任务申请记录
        param.getProject().setStatus(Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE);
        param.getProject().setFileSavePath(filePath);
        param.getProject().setCreateUserId(param.getSysUser().getId());
        param.getProject().setCreateTime(new Date());
        param.getProject().setLastUpdateTime(new Date());
        insert(param.getProject());

        // 生成分组申请
        Group group = new Group();
        group.setGroupName(param.getProject().getProjectName());
        group.setCreateTime(new Date());
        group.setLastUpdateTime(new Date());
        group.setCreateUserId(param.getSysUser().getId());
        group.setStatus(Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE);
        groupDao.insert(group);
    }

    /**
     * 检查文件是否为系统支持的类型
     *
     * @param fileName 上传文件名
     * @return
     */
    private boolean checkFileSuffix(String fileName) {
        String suffix = fileName.substring(fileName.indexOf("."));
        if (!validSuffixs.contains(suffix))
            return false;
        return true;
    }

    /**
     * 插入评审任务申请记录
     *
     * @param project
     */
    public void insert(Project project) {
        if (project != null) {
            projectDao.insert(project);
        }
    }

    /**
     * 查询projects
     *
     * @param param
     * @return
     */
    @Override
    public List<Project> listProject(ProjectListParam param) {
        if (param != null) {
            return projectDao.listProject(param);
        }
        return null;
    }

    /**
     * 查询总记录数
     *
     * @param param
     * @return
     */
    @Override
    public int getTotalCount(ProjectListParam param) {
        if (param != null) {
            return projectDao.getTotalCount(param);
        }
        return 0;
    }

    /**
     * 更新申请记录
     *
     * @param project
     * @return
     */
    @Override
    public int update(Project project) {
        if (project != null) {
            return projectDao.update(project);
        }
        return 0;
    }

    /**
     * 通过id查询评审任务申请记录
     *
     * @param id
     * @return
     */
    @Override
    public Project getProjectById(Long id) {
        if (id != null) {
            return projectDao.getProjectById(id);
        }
        return null;
    }

    /**
     * 通过id删除评审任务申请记录
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        if (id != null) {
            projectDao.deleteById(id);
        }
    }

    /**
     * 查找分组下的所有评审任务
     *
     * @param groupId
     * @return
     */
    @Override
    public List<Project> listByGroupId(Long groupId) {
        if (groupId != null) {
            List<Project> projects = projectDao.listByGroupId(groupId);
            return projects;
        }
        return null;
    }

    /**
     * 根据列表id更新状态，避免循环单次更新以提高效率
     *
     * @param projectIds
     */
    @Override
    public void updateStatusByIds(List<Long> projectIds, String status) {
        if (!CollectionUtils.isEmpty(projectIds)) {
            projectDao.updateStatusByIds(projectIds, status);
        }
    }

    @Override
    public int getReivewdTotalCount(ProjectListParam param) {
        if (param != null) {
            return projectDao.getReivewdTotalCount(param);
        }
        return 0;
    }

    @Override
    public List<Project> listReivewdtProject(ProjectListParam param) {
        if (param != null) {
            return projectDao.listReivewdtProject(param);
        }
        return null;
    }

    @Override
    public void updateReviewCountById(Long id) {
        projectDao.updateReviewCountById(id);
    }
}
