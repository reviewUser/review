package com.chinasoft.dao;

import com.chinasoft.entity.CheckReviewStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckReviewDao {

    void insert(CheckReviewStatus checkReview);

    void updateStatus(CheckReviewStatus checkReview);

    @Select("SELECT * FROM check_review_status WHERE review = #{reviewId}")
    List<CheckReviewStatus> queryStatusByReviewId(@Param("reviewId") long reviewId);

    @Select("SELECT * FROM check_review_status WHERE review = #{reviewId} AND phone = #{phone}")
    CheckReviewStatus queryByReviewIdAndPhone(@Param("reviewId") long reviewId, @Param("phone") String phone);
}
