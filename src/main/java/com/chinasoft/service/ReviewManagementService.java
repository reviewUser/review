package com.chinasoft.service;

import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.ReviewManagement;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 系统用户业务层接口
 *
 * @author 王鹏
 */
public interface ReviewManagementService {

    /**
     * 通过name和角色搜索用户
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
     * 导入评审任务信息
     *
     * @param file
     * @return
     */
    Map<String, Object> importReview(MultipartFile file);

    String delReviews(List<Long> ids);

    /**
     * 发起评审
     *
     * @param reviewManagement
     */
    Map<String, Object> startReview(ReviewManagement reviewManagement) throws Exception;

    /**
     * 导出评审任务信息
     *
     * @param ids
     * @return
     */
    void  exportReview(List<Long> ids, HttpServletResponse response);
}
