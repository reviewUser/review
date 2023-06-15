package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 页面跳转
 */
@Controller
public class PageJumpController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping({ "/", "/index" })
    public String index() {
        return "login";
    }

    /**
     * 专家信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/expertInfo")
    public String expertInfoPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        return "system/expertInfoPage";
    }

    /**
     * 评审任务管理
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/reviewManagement")
    public String reviewManagementPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        return "system/reviewManagementPage";
    }

    /**
     * 密码修改
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/pswManagement")
    public String pswManagementPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }

        return "system/pswManagementPage";
    }

    /**
     * 系统配置
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/sysConfig")
    public String sysConfigPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        return "system/sysConfigPage";
    }
}
