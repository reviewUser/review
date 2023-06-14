package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String groupManagePage(HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        return "system/groupManage/groupManage";
    }

    /**
     * 跳转评审任务管理页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/reviewManagement")
    public String managePage(HttpSession session, HttpServletRequest request) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        return "system/projectManage/manage";
    }

    /**
     * 跳转密码修改页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/passwordManagement")
    public String allocateManagePage(HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }

        return "system/allocateManage/allocateManage";
    }

    /**
     * 跳转系统配置
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/sysConfig")
    public String fieldPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        return "system/fieldManage/fieldManage";
    }
}
