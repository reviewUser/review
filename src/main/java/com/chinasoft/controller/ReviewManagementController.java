package com.chinasoft.controller;

import com.chinasoft.dao.CheckReviewDao;
import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.CheckReview;
import com.chinasoft.po.ReviewManagement;
import com.chinasoft.service.ReviewManagementService;
import com.chinasoft.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Result delExpert(@RequestBody List<Long> ids) throws IOException {
        try {
            return reviewManagementService.delReviews(ids);
        } catch (RuntimeException e) {
            e.getMessage();
        }
        return null;
    }

    @PostMapping(value = "/startReview")
    public Result startReview(@RequestBody ReviewManagement reviewManagement) throws IOException {
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
    public void exportReviewExcel(@RequestBody List<Long> ids, HttpServletResponse response) {
        try {
            reviewManagementService.exportReview(ids, response);
        } catch (RuntimeException e) {
            e.getMessage();
        }
    }

    /**
     * 查询评审任务短信回复详情
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/queryRepeatMsg")
    public List<CheckReview> queryRepeatMsg(@RequestParam Long id) {
        try {
            return reviewManagementService.queryRepeatMsg(id);
        } catch (RuntimeException e) {
            e.getMessage();
        }
        return null;
    }
}