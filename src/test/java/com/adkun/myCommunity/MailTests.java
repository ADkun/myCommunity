package com.adkun.myCommunity;

import com.adkun.myCommunity.util.MailClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ContextConfiguration(classes = MyCommunityApplication.class)
@RunWith(SpringRunner.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;


    @Test
    public void testMail() {
        String to = "1072112128@qq.com";
        String subject = "Hello world";
        String text = "<h1>哈哈</h1><br><br><p style='color: red;'>你好<p>";

        mailClient.sendMail(to, subject, text);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
