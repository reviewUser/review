package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class PageJumpController {
    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping({ "/", "/index" })
    public String index() {
        return "login";
    }

    /**
     * 跳转专家信息页面
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
     * 跳转评审任务管理页面
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
     * 跳转密码修改页面
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
     * 跳转系统配置
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
