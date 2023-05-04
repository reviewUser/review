package com.chinasoft.dao;

import com.chinasoft.po.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewDao {

	void insert(Review review);

	List<Review> listByProjectId(Long id);

	Review getByExpertIdAndProjectId(@Param("expertId")Long expertId, @Param("projectId")Long projectId);

}
