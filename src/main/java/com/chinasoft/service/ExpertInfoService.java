package com.chinasoft.service;

import com.chinasoft.param.ExpertParam;
import com.chinasoft.entity.ExpertInfo;
import com.chinasoft.po.PwdInfo;
import com.chinasoft.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 系统用户业务层接口
 */
public interface ExpertInfoService {

    int insert(ExpertInfo expertInfo) throws ParseException;

    List<ExpertInfo> queryExpertInfo(ExpertParam param);

    int getTotalCount(ExpertParam param);

    Map<String, Object> importExpert(MultipartFile file);

    void exportExpert(List<Long> ids, HttpServletResponse response) throws IOException;

    Result delExperts(List<Long> ids);

    /**
     * 未参会次数
     *
     * @param unMeeting
     */
    int updateUnMeetingNum(int unMeeting, long id);

    int unBan(List<Long> ids);

    /**
     * 修改密码
     *
     * @param pwdInfo
     */
    Result updatePwd(PwdInfo pwdInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
