package com.chinasoft.dao;

import com.chinasoft.entity.CheckReviewStatus;
import com.chinasoft.entity.ExpertInfo;
import com.chinasoft.entity.RepeatMessageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RepeatMessageDao {
    void insert(RepeatMessageInfo repeatMessageInfo);

    void updateByPhone(RepeatMessageInfo repeatMessageInfo);

    @Select("SELECT * FROM repeat_message WHERE PHONE = #{phone}")
    RepeatMessageInfo queryMsgByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM repeat_message")
    List<RepeatMessageInfo> queryAllMsg();

    @Delete("DELETE FROM repeat_message WHERE PHONE = #{phone}")
    void delMsgByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM check_review_status WHERE review = #{id}")
    List<CheckReviewStatus> queryRepeatByReviewId(@Param("id") long id);

    @Select("SELECT * FROM expert_info WHERE phone = #{phone}")
    ExpertInfo queryName(@Param("phone") String phone);
}
