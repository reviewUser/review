package com.chinasoft.service;

import com.chinasoft.param.ExpertParam;
import com.chinasoft.po.ExpertInfo;
import com.chinasoft.po.PwdInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * 系统用户业务层接口
 *
 * @author 王鹏
 */
public interface ExpertInfoService {

    /**
     * 新增专家信息
     *
     * @param expertInfo
     */
    void insert(ExpertInfo expertInfo);

    /**
     * 通过name和角色搜索用户
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
     * 导入专家信息
     *
     * @param file
     * @return
     */
    Map<String, Object> importExpert(MultipartFile file);

    /**
     * 导出专家信息
     *
     * @param ids
     * @return
     */
    void  exportExpert(List<Long> ids, HttpServletResponse response) throws IOException;

    /**
     * 删除专家信息
     *
     * @param ids
     */
    void delExperts(List<Long> ids);

    /**
     * 修改密码
     *
     * @param pwdInfo
     */
    Map<String, Object> updatePwd(PwdInfo pwdInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
