package com.adkun.myCommunity.service.impl;

import com.adkun.myCommunity.dao.DiscussPostMapper;
import com.adkun.myCommunity.entity.DiscussPost;
import com.adkun.myCommunity.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author adkun
 */
@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    private final DiscussPostMapper postMapper;

    public DiscussPostServiceImpl(DiscussPostMapper discussPostMapper) {
        postMapper = discussPostMapper;
    }


    @Override
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return postMapper.selectDiscussPosts(userId, offset, limit);
    }

    @Override
    public int getDiscussPostsRows(int userId) {
        return postMapper.selectDiscussPostsRows(userId);
    }
}
