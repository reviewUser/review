package com.chinasoft.service.impl;

import com.chinasoft.param.GroupAllocateParam;
import com.chinasoft.po.Allocate;
import com.chinasoft.po.Project;
import com.chinasoft.service.AllocateService;
import com.chinasoft.service.GroupService;
import com.chinasoft.service.ProjectService;
import com.chinasoft.dao.GroupDao;
import com.chinasoft.param.GroupListParam;
import com.chinasoft.po.Group;
import com.chinasoft.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评审任务分组业务层接口实现
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private AllocateService allocateService;

    @Autowired
    private ProjectService projectService;

    /**
     * 添加评审任务分组
     *
     * @param group
     * @param user
     */
    @Override
    public void insert(Group group, SysUser user) {
        if (group != null) {
            group.setCreateTime(new Date());
            group.setLastUpdateTime(new Date());
            group.setCreateUserId(user.getId());
            group.setStatus(Group.PROJECT_GROUP_STATUS_WAIT_ADD_PROJECT);
            groupDao.insert(group);
        }
    }

    /**
     * 得到总记录数
     *
     * @param param
     * @return
     */
    @Override
    public int getTotalCount(GroupListParam param) {
        if (param != null) {
            return groupDao.getTotalCount(param);
        }
        return 0;
    }

    /**
     * 查询分组
     *
     * @param param
     * @return
     */
    @Override
    public List<Group> listGroup(GroupListParam param) {
        if (param != null) {
            return groupDao.listGroup(param);
        }
        return null;
    }

    /**
     * 修改分组信息
     */
    @Override
    public int update(Group group) {
        if (group != null) {
            return groupDao.update(group);
        }
        return 0;
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Group getById(Long id) {
        if (id == null) {
            return null;
        }
        Group group = groupDao.getById(id);
        return group;
    }

    @Transactional
    @Override
    public int allocate(GroupAllocateParam param) {
        Allocate allocate = new Allocate();
        allocate.setAllocateUserId(param.getUser().getId());
        allocate.setExpertId(param.getExpertId());
        allocate.setGroupId(param.getGroupId());
        allocate.setAllocateTime(new Date());
        int num = allocateService.insert(allocate);

        Group group = getById(param.getGroupId());
        group.setStatus(Group.PROJECT_GROUP_STATUS_ALLOCATE_DONE);
        group.setLastUpdateTime(new Date());
        update(group);

        List<Project> projects = projectService.listByGroupId(param.getGroupId());
        List<Long> projectIds = new ArrayList<Long>();

        if (!CollectionUtils.isEmpty(projects)) {
            for (Project project : projects) {
                projectIds.add(project.getId());
            }
            projectService.updateStatusByIds(projectIds, Project.PROJECT_REVIEW_STATUS_WAIT_REVIEW);
        }
        return num;
    }


}
