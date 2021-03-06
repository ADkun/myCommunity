package com.adkun.myCommunity.controller;

import com.adkun.myCommunity.entity.User;
import com.adkun.myCommunity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取注册页面GET
     *
     * @return
     */
    @GetMapping("/register")
    public String getRegisterPage() {
        return "site/register";
    }

    /**
     * 注册表单提交
     *
     * @param username 用户名
     * @param password 密码
     * @param confirm  确认密码
     * @param email    邮箱
     * @param model    视图model
     * @return
     */
    @PostMapping("/register")
    public String register(
            String username, String password, String confirm, String email, Model model
    ) {
        // 调用UserService的注册组件
        Map<String, Object> map = userService.register(username, password, confirm, email);
        if (!map.containsKey("ok")) {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("confirmMsg", map.get("confirmMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "site/register";
        } else {
            model.addAttribute("msg", "注册成功，我们已经向" + ((User) map.get("user")).getEmail() + "发送了一封邮件");
            model.addAttribute("target", "/community/index");
            return "site/operate-result";
        }
    }
}
