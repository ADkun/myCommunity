package com.adkun.myCommunity.service;

import com.adkun.myCommunity.entity.DiscussPost;

import java.util.List;

public interface DiscussPostService {

    /**
     * 根据用户id找到对应帖子
     * 如果id == 0，返回所有帖子
     * @param userId 用户id(0为所有帖子）
     * @param offset 分页当前页
     * @param limit 每页限制
     * @return 帖子列表
     */
    List<DiscussPost> findDiscussPosts(int userId, int offset, int limit);

    /**
     * 根据用户id找到对应帖子
     * 返回数量
     * @param userId 用户id(0为所有帖子）
     * @return 帖子数量
     */
    int getDiscussPostsRows(int userId);
}
