package com.adkun.myCommunity.service;

import com.adkun.myCommunity.dao.DiscussPostMapper;
import com.adkun.myCommunity.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author adkun
 */
@Service
public class DiscussPostService {

    private final DiscussPostMapper postMapper;

    public DiscussPostService(DiscussPostMapper discussPostMapper) {
        postMapper = discussPostMapper;
    }

    /**
     * 根据用户id找到对应帖子
     * 如果id == 0，返回所有帖子
     * @param userId 用户id(0为所有帖子）
     * @param offset 分页当前页
     * @param limit 每页限制
     * @return 帖子列表
     */
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return postMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int getDiscussPostsRows(int userId) {
        return postMapper.selectDiscussPostsRows(userId);
    }
}
