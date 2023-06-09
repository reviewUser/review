package com.chinasoft.service;

import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.CheckReview;
import com.chinasoft.po.QueryDescVo;
import com.chinasoft.po.ReviewManagement;
import com.chinasoft.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 系统用户业务层接口
 */
public interface ReviewManagementService {

    List<ReviewManagement> queryReviewInfo(ReviewParam param);

    int getReviewTotalCount(ReviewParam param);

    Map<String, Object> importReview(MultipartFile file);

    Result delReviews(List<Long> ids);

    Result startReview(ReviewManagement reviewManagement) throws Exception;

    void exportReview(List<Long> ids, HttpServletResponse response);

    List<QueryDescVo> queryRepeatMsg(Long id);

    Result addParticipants(ReviewManagement reviewManagement) throws Exception;
}
