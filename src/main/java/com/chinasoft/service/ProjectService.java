package com.chinasoft.service;

import com.chinasoft.param.GenerateApplyParam;
import com.chinasoft.param.ProjectListParam;
import com.chinasoft.po.Project;

import java.util.List;

/**
 * 评审任务相关业务层接口
 * @author 王鹏
 *
 */
public interface ProjectService {

	/**
	 * 检查文件是否为系统支持的类型
	 * @param fileName 上传文件名
	 * @return
	 */
//	boolean checkFileSuffix(String fileName);

	/**
	 * 插入评审任务申请记录
	 * @param project
	 */
	void insert(Project project);

	/**
	 * 生成申请记录
	 * @param GenerateApplyParam param 记录生成参数
	 */
	void generateApply(GenerateApplyParam param);

	/**
	 * 查询projects
	 * @param param
	 * @return
	 */
	List<Project> listProject(ProjectListParam param);

	/**
	 * 查询总记录数
	 * @param param
	 * @return
	 */
	int getTotalCount(ProjectListParam param);

	/**
	 * 更新申请记录
	 * @param project
	 * @return
	 */
	int update(Project project);

	/**
	 * 通过id查询评审任务申请记录
	 * @param id
	 * @return
	 */
	Project getProjectById(Long id);

	/**
	 * 通过id删除评审任务申请记录
	 * @param id
	 */
	void deleteById(Long id);

	/**
	 * 查找分组下的所有评审任务
	 * @param groupId
	 * @return
	 */
	List<Project> listByGroupId(Long groupId);

	/**
	 * 根据列表id更新状态，避免循环单次更新以提高效率
	 * @param projectIds
	 */
	void updateStatusByIds(List<Long> projectIds, String status);

	/**
	 * 某专家已评审评审任务总记录数
	 * @param param
	 * @return
	 */
	int getReivewdTotalCount(ProjectListParam param);

	/**
	 * 某专家已评审评审任务
	 * @param param
	 * @return
	 */
	List<Project> listReivewdtProject(ProjectListParam param);

	void updateReviewCountById(Long id);

}
