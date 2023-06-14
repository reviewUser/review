package com.chinasoft.controller;

import com.chinasoft.exception.CustomException;
import com.chinasoft.entity.SysUser;
import com.chinasoft.service.UserService;
import com.chinasoft.utils.Md5Utils;
import com.chinasoft.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(SysUser sysUser, HttpSession session, Model model) throws Exception {
        if (sysUser != null) {
            if (sysUser.getPassword() != null) {
                sysUser.setPassword(Md5Utils.md5(sysUser.getPassword()));
            }
        }
        SysUser user = userService.login(sysUser);
        if (user == null) {
            model.addAttribute("message", "请正确填写角色、用户名与密码");
            return "login";
        }
        session.setAttribute("user", user);
//        return "forward:/user/" + user.getRoleId() + "/afterLoginPage";
        return "forward:/user/" + "3" + "/afterLoginPage";

    }

    /**
     * 用户退出登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        ;
        return "login";

    }

    /**
     * 登陆后跳转的页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/{role}/afterLoginPage")
    public String afterLoginPage(HttpSession session, @PathVariable String role) {
        if (session.getAttribute("user") == null) {
            throw new CustomException("未登录，请先登录", "/");
        }
        session.setAttribute("token", UUIDUtils.generateUUIDString());
        String path = null;
        switch (role) {
            case "1":
                path = "system/expertInfoPage";
                break;
            case "2":
                path = "system/expertInfoPage";
                break;
            case "3":
                path = "system/expertInfoPage";
                break;
            default:
                break;
        }

        return path;
    }
}
