package com.chinasoft.dao;

import com.chinasoft.po.CheckReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckReviewDao {

    void insert(CheckReview checkReview);

//    @Select("UPDATE check_review_status SET status = '1'  where phone = #{phone}")
//    void updateStatus(@Param("phone") String phone);

    void updateStatus(CheckReview checkReview);

    @Select("SELECT * FROM check_review_status WHERE review = #{reviewId}")
    List<CheckReview> queryStatusByReviewId(@Param("reviewId") long reviewId);

    @Select("SELECT * FROM check_review_status WHERE review = #{reviewId} AND phone = #{phone}")
    CheckReview queryByReviewIdAndPhone(@Param("reviewId") long reviewId, @Param("phone") String phone);
}
