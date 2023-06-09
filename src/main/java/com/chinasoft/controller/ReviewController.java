package com.chinasoft.controller;

import com.alibaba.druid.util.StringUtils;
import com.chinasoft.exception.CustomException;
import com.chinasoft.param.ProjectListParam;
import com.chinasoft.po.Project;
import com.chinasoft.po.Result;
import com.chinasoft.po.Review;
import com.chinasoft.po.SysUser;
import com.chinasoft.service.ProjectService;
import com.chinasoft.service.ReviewService;
import com.chinasoft.service.UserService;
import com.chinasoft.utils.CustomDateUtils;
import com.chinasoft.utils.FileUtils;
import com.chinasoft.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    /**
     * 评审任务评审页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/reviewPage")
    public String reviewPage(HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        return "expert/review";
    }

    /**
     * 我（专家）的评审页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/reviewInfoPage")
    public String reviewInfoPage(Model model, HttpSession session) throws Exception {
        SysUser user = (SysUser) session.getAttribute("user");
        if (user == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        model.addAttribute("expertId", user.getId());
        return "expert/reviewinfo";
    }

    /**
     * 下载评审任务文件
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/download")
    public String download(Model model, HttpSession session, HttpServletResponse res, HttpServletRequest req, Long id) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        Project p = projectService.getProjectById(id);
        if (p == null) {
            model.addAttribute("message", "该评审任务申请已不存在，请刷新后重试");
        }
        String messgage = FileUtils.download(p.getFileSavePath(), res, req);
        model.addAttribute("message", messgage);
        return "expert/review";
    }

    /**
     * 打分页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/gradePage")
    public String gradePage(Model model, HttpSession session, Long projectId, Long applicantId, Long groupId) throws Exception {
        SysUser user = (SysUser) session.getAttribute("user");
        if (user == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        if (projectId != null) {
            Review review = reviewService.getByExpertIdAndProjectId(user.getId(), projectId);
            if (review != null) {
                throw new CustomException("你已评审过该评审任务！！", "/review/reviewPage");
            }
        }

        model.addAttribute("projectId", projectId);
        model.addAttribute("applicantId", applicantId);
        model.addAttribute("groupId", groupId);
        session.setAttribute("token", UUIDUtils.generateUUIDString());
        return "expert/grade";
    }

    /**
     * 评审结果页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/resultPage")
    public String resultPage(Model model, HttpSession session, Long id) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        model.addAttribute("projectId", id);
        return "expert/result";
    }

    /**
     * 打分
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/grade")
    public String grade(Model model, HttpSession session, Review review, String token, Long groupId) throws Exception {
        SysUser user = (SysUser) session.getAttribute("user");
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        if (session.getAttribute("token") == null) {
            model.addAttribute("message", "请勿重复提交表单");
            return "expert/reviewinfo";
        }
        if (session.getAttribute("token").equals(token)) {
            reviewService.review(review, user, groupId);
            model.addAttribute("message", "评审打分成功");
            session.removeAttribute("token");
        }
        model.addAttribute("expertId", review.getExpertId());
        return "expert/reviewinfo";
    }

    /**
     * 查找评审结果
     *
     * @param session
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/result")
    public Map<String, Object> result(HttpSession session, Long id) {
        SysUser user = (SysUser) session.getAttribute("user");
        if (user == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        List<Review> reviews = reviewService.listByProjectId(id);
        formatReviews(reviews);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("count", 0);
        map.put("msg", "");
        map.put("data", reviews);
        return map;
    }

    /**
     * 最终评审结果
     *
     * @param session
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/lastResult")
    public Map<String, Object> lastResult(HttpSession session, Long id) {
        SysUser user = (SysUser) session.getAttribute("user");
        if (user == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        List<Review> reviews = reviewService.listByProjectId(id);
        double sum = 0;
        for (Review review : reviews) {
            sum += Double.parseDouble(review.getGrade());
        }
        Result r = new Result();
        r.setAverageGrade(sum / reviews.size());
        if (r.getAverageGrade() >= 60) {
            r.setResult("评审通过");
        } else {
            r.setResult("评审未通过");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("count", 0);
        map.put("msg", "");
        map.put("data", Arrays.asList(r));
        return map;
    }

    private void formatReviews(List<Review> reviews) {
        for (Review review : reviews) {
            SysUser user = userService.getUserById(review.getExpertId());
            if (user == null) {
                throw new CustomException("用户不存在", "/");
            }
            review.setExpertName(user.getUsername());
            if (Review.REVIEW_RESULT_PASS.equals(review.getGrade())) {
                review.setGrade("评审通过");
            }
            if (Review.REVIEW_RESULT_REJECT.equals(review.getGrade())) {
                review.setGrade("评审未通过");
            }

        }
    }

    /**
     * 查询专家已评审评审任务申请记录接口，以json的形式返回
     *
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/projects.json")
    public Map<String, Object> getProjectsJson(HttpSession session, HttpServletRequest request, Long expertId) {
        Map<String, Object> map = new HashMap<String, Object>();
        String projectName = request.getParameter("projectName");
        String createTime = request.getParameter("createTime");
        int limit = Integer.valueOf(request.getParameter("limit"));
        List<Long> createUserIds = null;
        int offset = (Integer.valueOf(request.getParameter("page")) - 1) * limit;
        // 生成查询参数
        ProjectListParam param = new ProjectListParam();
        if (!StringUtils.isEmpty(projectName))
            param.setProjectName(projectName.trim());
        String createUserName = request.getParameter("username");
        if (!StringUtils.isEmpty(createUserName)) {
            createUserIds = userService.getUserByUserName(createUserName.trim());
            param.setCreateUserIds(createUserIds);
            if (CollectionUtils.isEmpty(createUserIds)) {
                map.put("code", 0);
                map.put("count", 0);
                map.put("msg", "");
                map.put("data", new ArrayList<Project>());
                return map;
            }
        }

        // 设置正确的日期格式
        if (!StringUtils.isEmpty(createTime))
            CustomDateUtils.setTimeRange(param, createTime);
        param.setExpertId(expertId);
        param.setOffset(offset);
        param.setLimit(limit);

        int totalCount = projectService.getReivewdTotalCount(param);
        List<Project> projects = projectService.listReivewdtProject(param);
        // 因为layui数据表格的限制，故将数据转成表格需要的格式
        formatProject(projects);
        map.put("code", 0);
        map.put("count", totalCount);
        map.put("msg", "");
        map.put("data", projects);
        return map;
    }

    /**
     * 因为layui数据表格的限制，故将数据转成表格需要的格式
     *
     * @param projects
     */
    private void formatProject(List<Project> projects) {
        for (Project project : projects) {
            project.setCreateUserName(project.getUser().getUsername());
//			if (Project.PROJECT_REVIEW_STATUS_WAIT_GROUP.equals(project.getStatus())) {
//				project.setStatus("待分组");
//			}
            if (Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE.equals(project.getStatus())) {
                project.setStatus("待分配");
            }
            if (Project.PROJECT_REVIEW_STATUS_WAIT_REVIEW.equals(project.getStatus())) {
                project.setStatus("待评审");
            }
            if (Project.PROJECT_REVIEW_STATUS_IN_REVIEW.equals(project.getStatus())) {
                project.setStatus("评审中");
            }
            if (Project.PROJECT_REVIEW_STATUS_REVIEW_DONE.equals(project.getStatus())) {
                project.setStatus("评审完成");
            }
        }
    }
}
