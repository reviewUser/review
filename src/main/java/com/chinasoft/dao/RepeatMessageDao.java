package com.chinasoft.dao;

import com.chinasoft.po.CheckReview;
import com.chinasoft.po.RepeatMessageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RepeatMessageDao {

    /**
     * 新增专家短信回复
     *
     * @param repeatMessageInfo
     */
    void insert(RepeatMessageInfo repeatMessageInfo);

    /**
     * 修改专家短信回复
     *
     * @param repeatMessageInfo
     */
    void updateByPhone(RepeatMessageInfo repeatMessageInfo);

    /**
     * 根据手机查询其回复内容
     *
     * @param phone
     * @return
     */
    @Select("SELECT * FROM repeat_message WHERE PHONE = #{phone}")
    RepeatMessageInfo queryMsgByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM repeat_message")
    List<RepeatMessageInfo> queryAllMsg();

    @Delete("DELETE FROM repeat_message WHERE PHONE = #{phone}")
    void delMsgByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM check_review_status WHERE review = #{id}")
    List<CheckReview> queryRepeatByReviewId(@Param("id") long id);
}
