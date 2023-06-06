package com.chinasoft.dao;

import com.chinasoft.param.ExpertParam;
import com.chinasoft.po.ExpertInfo;
import com.chinasoft.po.ReviewManagement;
import com.chinasoft.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExpertInfoDao {

    /**
     * 新增专家信息
     *
     * @param expertInfo
     */
    void insert(ExpertInfo expertInfo);

    /**
     * 更新专家信息
     *
     * @param expertInfo
     */
    void updateExpertByPhone(ExpertInfo expertInfo);


    /**
     * 查询所以专家信息
     *
     * @param param
     * @return
     */
    List<ExpertInfo> queryExpertInfo(ExpertParam param);

    /**
     * 得到总记录数
     *
     * @param param
     */
    int getTotalCount(ExpertParam param);

    /**
     * 批量导入专家信息
     *
     * @param expertInfoList
     * @return
     */
    int batchInsertExperts(List<ExpertInfo> expertInfoList);

    /**
     * 批量删除专家信息
     *
     * @param ids
     * @return
     */
    int batchDelExperts(List<Long> ids);

    /**
     * 根据手机查询专家信息
     *
     * @param phone
     * @return
     */
    @Select("SELECT * FROM expert_info WHERE PHONE = #{phone}")
    ExpertInfo queryExpertByPhone(@Param("phone") String phone);

    @Select("UPDATE expert_info SET expert_status = '封禁'  where id = #{id}")
    void updateExpertStatus(@Param("id") long id);

    /**
     * 根据ids查询专家信息
     *
     * @param ids
     * @return
     */
    List<ExpertInfo> queryExpertByIds(@Param("ids") List<Long> ids);

    @Select("SELECT * FROM expert_info")
    List<ExpertInfo> queryAllExpert();

    void updateUnMeetingNum(@Param("unMeeting") int unMeeting, @Param("id") long id);

    @Select("SELECT * FROM sys_user where username = #{username}")
    SysUser queryUser(@Param("username") String username);

    @Select("UPDATE sys_user SET password = #{password}  where username = #{username}")
    void updatePwd(@Param("password") String password, @Param("username") String username);

    @Select("SELECT * from expert_info where work_number = #{workNumber}")
    ExpertInfo selectByWorkNum(@Param("workNumber") String workNumber);
}
