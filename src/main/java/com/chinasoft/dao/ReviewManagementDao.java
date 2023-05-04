package com.chinasoft.dao;

import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.ExpertInfo;
import com.chinasoft.po.ReviewManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewManagementDao {
    /**
     * 查询所以专家信息
     *
     * @param param
     * @return
     */
    List<ReviewManagement> queryReviewInfo(ReviewParam param);

    /**
     * 得到总记录数
     *
     * @param param
     */
    int getReviewTotalCount(ReviewParam param);

    /**
     * 批量评审任务信息
     *
     * @param reviewManagements
     * @return
     */
    int batchInsertReviews(List<ReviewManagement> reviewManagements);

    /**
     * 批量导入专家信息
     *
     * @param ids
     * @return
     */
    int batchDelReviews(List<Long> ids);

    @Select("SELECT * from expert_info where expert_status = '正常' and field_name = #{reviewFiled}")
    List<ExpertInfo> queryExpertByFiled(@Param("reviewFiled") String reviewFiled);

    @Select("UPDATE review_management SET review_status = #{reviewStatus}  where id = #{id}")
    void updateStatus(@Param("reviewStatus") String reviewStatus, @Param("id") long id);

    /**
     * 根据ids查询评审任务信息
     *
     * @param ids
     * @return
     */
    List<ReviewManagement> queryReviewByIds(@Param("ids") List<Long> ids);

    @Select("SELECT * FROM review_management")
    List<ReviewManagement> queryAllReviews();
}
