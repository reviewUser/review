package com.chinasoft.service;

import com.chinasoft.po.Review;
import com.chinasoft.po.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

	/**
	 * 插入
	 * @param group
	 */
	void insert(Review review);

	/**
	 * 打分
	 * @param review
	 * @param attribute
	 */
	void review(Review review, SysUser user,Long groupId);

	List<Review> listByProjectId(Long id);

	Review getByExpertIdAndProjectId(Long expertId, Long projectId);
}
