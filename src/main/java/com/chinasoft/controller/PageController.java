package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pageJump")
public class PageController {
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
