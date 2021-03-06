package com.adkun.myCommunity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = MyCommunityApplication.class)
@RunWith(SpringRunner.class)
public class DemoTests {

    @Test
    public void testVoidMap() {
        Map<String, Object> map = new HashMap<>();
        System.out.println(map.get("123"));
    }
}
