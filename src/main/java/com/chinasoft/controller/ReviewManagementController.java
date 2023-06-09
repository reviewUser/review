package com.chinasoft.controller;

import com.chinasoft.param.ReviewParam;
import com.chinasoft.po.QueryDescVo;
import com.chinasoft.entity.ReviewManagement;
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

/**
 * 评审任务管理页面
 */
@RestController
@RequestMapping("/review")
public class ReviewManagementController {

    @Autowired
    private ReviewManagementService reviewManagementService;

    /**
     * 查询
     * @param param
     * @return
     */
    @PostMapping("/queryReviewInfo")
    public Map<String, Object> queryReviewInfo(@RequestBody ReviewParam param) {
        Map<String, Object> map = new HashMap<String, Object>();
        int pageNum = (param.getPageNum() - 1) * param.getPageSize();

        List<ReviewManagement> users = null;
        int totalCount = 0;
        // 页码
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
     * 导入
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/importReviewExcel")
    public Map<String, Object> excelProTbZbzs(MultipartFile file) {
        Map<String, Object> stringObjectMap = reviewManagementService.importReview(file);
        return stringObjectMap;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delReview")
    public Result delExpert(@RequestBody List<Long> ids) throws IOException {
        return reviewManagementService.delReviews(ids);
    }

    /**
     * 发起评审
     * @param reviewManagement
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/startReview")
    public Result startReview(@RequestBody ReviewManagement reviewManagement) throws Exception {
        return reviewManagementService.startReview(reviewManagement);
    }

    /**
     * 追加参会人
     * @param reviewManagement
     * @return
     * @throws Exception
     */
    @PostMapping("/addParticipants")
    public Result addParticipants(@RequestBody ReviewManagement reviewManagement) throws Exception {
        return reviewManagementService.addParticipants(reviewManagement);
    }

    /**
     * 导出
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/exportReviewExcel")
    public void exportReviewExcel(@RequestBody List<Long> ids, HttpServletResponse response) {
        reviewManagementService.exportReview(ids, response);
    }

    /**
     * 查询评审任务短信回复详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryRepeatMsg")
    public List<QueryDescVo> queryRepeatMsg(@RequestParam Long id) {
        return reviewManagementService.queryRepeatMsg(id);
    }
}
