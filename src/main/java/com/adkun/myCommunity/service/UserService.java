package com.adkun.myCommunity.service;

import com.adkun.myCommunity.dao.UserMapper;
import com.adkun.myCommunity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户Id获取用户
     * @param userId 用户id
     * @return User类
     */
    public User findByUserId(int userId) {
        return userMapper.selectById(userId);
    }
}
