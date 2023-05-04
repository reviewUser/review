package com.chinasoft.controller;

import com.chinasoft.dao.CheckReviewDao;
import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.ReviewManagement;
import com.chinasoft.service.ReviewManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewManagementController {

    @Autowired
    private ReviewManagementService reviewManagementService;

    @Autowired
    private CheckReviewDao checkReviewDao;

    /**
     * 查询评审任务信息以json的形式返回
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/queryReviewInfo")
    public Map<String, Object> queryReviewInfo(@RequestBody ReviewParam param) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        int pageNum = (param.getPageNum() - 1) * param.getPageSize();

        List<ReviewManagement> users = null;
        int totalCount = 0;
        //页码
        param.setPageNum(pageNum);
        users = reviewManagementService.queryReviewInfo(param);
        totalCount = reviewManagementService.getReviewTotalCount(param);
        map.put("code", 200);
        map.put("count", totalCount);
        map.put("msg", "查询评审任务成功");
        map.put("data", users);
        return map;
    }

    /**
     * 导入评审任务
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/importReviewExcel")
    public Map<String, Object> excelProTbZbzs(MultipartFile file) throws IOException {
        try {
            Map<String, Object> stringObjectMap = reviewManagementService.importReview(file);
            return stringObjectMap;
        } catch (RuntimeException e) {
            e.getMessage();
        }
        return null;
    }

    @PostMapping(value = "/delReview")
    public Map<String, Object> delExpert(@RequestBody List<Long> ids) throws IOException {
        try {
            String s = reviewManagementService.delReviews(ids);
            Map<String, Object> map = new HashMap<>();
            map.put("msg", s);
            return map;
        } catch (RuntimeException e) {
            e.getMessage();
        }
        return null;
    }

    @PostMapping(value = "/startReview")
    public Map<String, Object> startReview(@RequestBody ReviewManagement reviewManagement) throws IOException {
        try {
            return reviewManagementService.startReview(reviewManagement);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 导出评审任务信息
     *
     * @param ids
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/exportReviewExcel")
    public void exportReviewExcel(@RequestBody List<Long> ids, HttpServletResponse response){
        try {
            reviewManagementService.exportReview(ids, response);
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }
}
