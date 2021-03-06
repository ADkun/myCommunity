package com.adkun.myCommunity.service;

import com.adkun.myCommunity.dao.UserMapper;
import com.adkun.myCommunity.entity.User;
import com.adkun.myCommunity.util.CommunityConstants;
import com.adkun.myCommunity.util.CommunityUtils;
import com.adkun.myCommunity.util.MailClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户user表相关服务
 *
 * @author adkun
 */
@Service
@Slf4j
public class UserService implements CommunityConstants {


    private final UserMapper userMapper;
    private final MailClient mailClient;
    private final TemplateEngine templateEngine;


    public UserService(UserMapper userMapper, MailClient mailClient, TemplateEngine templateEngine) {
        this.userMapper = userMapper;
        this.mailClient = mailClient;
        this.templateEngine = templateEngine;
    }


    /**
     * 根据用户Id获取用户
     *
     * @param userId 用户id
     * @return User类
     */
    public User findByUserId(int userId) {
        return userMapper.selectById(userId);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param confirm
     * @param email
     * @return
     */
    public Map<String, Object> register(String username, String password, String confirm, String email) {
        Map<String, Object> map = new HashMap<>();
        // 检查值
        if (username == null || StringUtils.isBlank(username)) {
            map.put("usernameMsg", "用户名不能为空！");
            return map;
        }
        if (password == null || StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空！");
            return map;
        }
        if (confirm == null || StringUtils.isBlank(confirm)) {
            map.put("confirmMsg", "确认密码不能为空！");
            return map;
        }
        if (email == null || StringUtils.isBlank(email)) {
            map.put("emailMsg", "邮箱不能为空！");
            return map;
        }

        // 检查用户名是否已被注册
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            if (user.getStatus() == 0) {
                map.put("usernameMsg", "用户名已注册但未激活，请检查激活邮件！");
                return map;
            }
            map.put("usernameMsg", "用户名已被注册！");
            return map;
        }
        // 检查确认密码是否正确
        if (!password.equals(confirm)) {
            map.put("confirmMsg", "确认密码错误");
            return map;
        }
        // 检查邮箱是否已被注册
        User tempU = userMapper.selectByEmail(email);
        if (tempU != null) {
            map.put("emailMsg", "邮箱已被注册！");
            return map;
        }

        // 注册用户
        // 密码加密
        // 生成5位salt
        String salt = CommunityUtils.generateUUID().substring(0, 5);
        String handledPassword = password + salt;
        // 加密
        String encryptedPassword = CommunityUtils.md5(handledPassword);

        // 生成激活码
        String activationCode = CommunityUtils.generateUUID();

        // 存入数据库
        User u = new User();
        u.setUsername(username);
        u.setPassword(encryptedPassword);
        u.setEmail(email);
        u.setCreateTime(new Date());
        u.setActivationCode(activationCode);
        u.setStatus(0);
        u.setType(0);

        int rows = userMapper.insertUser(u);
        log.info("新增" + rows + "个用户");

        // 发送邮件
        // 使用thymeleaf生成页面
        Context context = new Context();
        context.setVariable("email", u.getEmail());
        String url = DOMAIN + CONTEXT_PATH + "/activation?userId=" + u.getId() + "&activationCode=" + u.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);

        mailClient.sendMail(u.getEmail(), "激活您的帐号", content);

        map.put("ok", "ok");
        map.put("user", u);
        return map;
    }
}
