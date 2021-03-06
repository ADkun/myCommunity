package com.adkun.myCommunity.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@Slf4j
public class MailClient{

    private final JavaMailSender mailSender;
    private final ThreadPoolTaskExecutor taskExecutor;

    private String from = "adkun2021@sina.com";

    public MailClient(JavaMailSender mailSender, ThreadPoolTaskExecutor taskExecutor) {
        this.mailSender = mailSender;
        this.taskExecutor = taskExecutor;
    }

    /**
     * 发送邮件
     * @param to 目标邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Async
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            log.error("发送邮件错误！" + e.getMessage());
        }
    }
}
