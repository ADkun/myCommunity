package com.adkun.myCommunity.dao;

import com.adkun.myCommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 根据用户id查找某个用户
     * @param userId 用户id
     * @return
     */
    User selectById(int userId);

    /**
     * 根据用户名查找某个用户
     * @param username 用户名
     * @return
     */
    User selectByUsername(String username);

    /**
     * 插入新用户
     * @param user 用户实体
     * @return
     */
    int insertUser(User user);

    /**
     * 根据email找用户
     * @param email
     * @return
     */
    User selectByEmail(String email);
}
