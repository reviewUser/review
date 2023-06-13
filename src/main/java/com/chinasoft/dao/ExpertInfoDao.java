package com.chinasoft.dao;

import com.chinasoft.param.ExpertParam;
import com.chinasoft.entity.ExpertInfo;
import com.chinasoft.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExpertInfoDao {

    int insert(ExpertInfo expertInfo);

    void updateExpertByPhone(ExpertInfo expertInfo);

    List<ExpertInfo> queryExpertInfo(ExpertParam param);

    int getTotalCount(ExpertParam param);

    int batchInsertExperts(List<ExpertInfo> expertInfoList);

    int batchDelExperts(List<Long> ids);

    @Select("SELECT * FROM expert_info WHERE PHONE = #{phone}")
    ExpertInfo queryExpertByPhone(@Param("phone") String phone);

    @Select("UPDATE expert_info SET expert_status = '封禁'  where id = #{id}")
    void updateExpertStatus(@Param("id") long id);

    int unBan(@Param("ids") List<Long> ids);

    List<ExpertInfo> queryExpertByIds(@Param("ids") List<Long> ids);

    @Select("SELECT * FROM expert_info")
    List<ExpertInfo> queryAllExpert();

    int updateUnMeetingNum(@Param("unMeeting") int unMeeting, @Param("id") long id);

    @Select("SELECT meeting_times FROM expert_info WHERE id = #{id}")
    int queryMeetingTime(@Param("id") long id);

    @Update("UPDATE expert_info SET meeting_times = #{meetingTime} WHERE id = #{id}")
    void updateMeetingTime(@Param("meetingTime") int meetingTime, @Param("id") long id);

    @Select("SELECT * FROM sys_user where username = #{username}")
    SysUser queryUser(@Param("username") String username);

    @Select("UPDATE sys_user SET password = #{password}  where username = #{username}")
    void updatePwd(@Param("password") String password, @Param("username") String username);

    @Select("SELECT * from expert_info where work_number = #{workNumber}")
    ExpertInfo selectByWorkNum(@Param("workNumber") String workNumber);
}
