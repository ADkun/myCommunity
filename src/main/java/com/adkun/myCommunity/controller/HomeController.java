package com.adkun.myCommunity.controller;

import com.adkun.myCommunity.entity.DiscussPost;
import com.adkun.myCommunity.entity.Page;
import com.adkun.myCommunity.entity.User;
import com.adkun.myCommunity.service.DiscussPostService;
import com.adkun.myCommunity.service.UserService;
import com.adkun.myCommunity.service.impl.DiscussPostServiceImpl;
import com.adkun.myCommunity.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final DiscussPostService postService;
    private final UserService userService;

    public HomeController(DiscussPostServiceImpl postService, UserServiceImpl userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * 社区首页
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String getHomePage(Page page, Model model) {
        // 分页逻辑
        int rows = postService.getDiscussPostsRows(0);
        page.setRows(rows);
        page.setPath("/index");

        // 帖子
        List<DiscussPost> postList = postService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> postVoList = new ArrayList<>();
        if (postList != null) {
            for (DiscussPost post : postList) {
                // 把返回的帖子与对应的User存入一个List里
                int userId = post.getUserId();
                User user = userService.findByUserId(userId);
                Map<String, Object> map = new HashMap<>();
                map.put("user", user);
                map.put("post", post);

                postVoList.add(map);
            }
        }
        // 添加model
        model.addAttribute("posts", postVoList);
        model.addAttribute("page", page);

        return "index";
    }
}
