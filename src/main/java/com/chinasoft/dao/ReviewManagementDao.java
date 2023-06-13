package com.chinasoft.dao;

import com.chinasoft.param.ReviewParam;
import com.chinasoft.entity.ExpertInfo;
import com.chinasoft.entity.ReviewManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReviewManagementDao {

    List<ReviewManagement> queryReviewInfo(ReviewParam param);

    int getReviewTotalCount(ReviewParam param);

    int batchInsertReviews(List<ReviewManagement> reviewManagements);

    int batchDelReviews(List<Long> ids);

    @Select("SELECT * from expert_info where expert_status = '正常' and field_name = #{reviewFiled} and address = #{sourceAddress}")
    List<ExpertInfo> queryExpertByFiled(@Param("reviewFiled") String reviewFiled, @Param("sourceAddress") String sourceAddress);

    @Select("SELECT phone FROM expert_info WHERE expert_status = '正常' and field_name = #{reviewFiled} and address = #{sourceAddress} ORDER BY meeting_times ASC , id ASC limit #{nums}")
    List<String> queryExperts(@Param("reviewFiled") String reviewFiled, @Param("sourceAddress") String sourceAddress, @Param("nums") int nums);

    @Select("SELECT meeting_times FROM expert_info WHERE phone = #{phone}")
    int queryMeetingTimesByPhone(String phone);

    @Update("UPDATE expert_info SET meeting_times = #{meetingTimes} WHERE phone = #{phone}")
    void updateMeetingTimesByPhone(String phone, int meetingTimes);

    @Select("UPDATE review_management SET review_status = #{reviewStatus}  where id = #{id}")
    void updateStatus(@Param("reviewStatus") String reviewStatus, @Param("id") long id);

    List<ReviewManagement> queryReviewByIds(@Param("ids") List<Long> ids);

    @Select("SELECT * FROM review_management")
    List<ReviewManagement> queryAllReviews();

    @Select("SELECT * from review_management where review_name = #{reviewName}")
    ReviewManagement selectByReviewName(@Param("reviewName") String reviewName);
}
