package com.adkun.myCommunity.dao;

import com.adkun.myCommunity.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     * 获取帖子
     * 可指定用户（userId != 0）
     * 或所有帖子（userId == 0）
     * @param userId 用户Id
     * @param offset 分页当前页
     * @param limit 分页每页限制
     * @return
     */
    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId, int offset, int limit);

    /**
     * 获取所有帖子数量
     * @param userId
     * @return
     */
    int selectDiscussPostsRows(@Param("userId") int userId);
}
