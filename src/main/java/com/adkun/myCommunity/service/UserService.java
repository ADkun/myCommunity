package com.adkun.myCommunity.service;

import com.adkun.myCommunity.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * 根据用户Id获取用户
     *
     * @param userId 用户id
     * @return User类
     */
    User findByUserId(int userId);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param confirm
     * @param email
     * @return
     */
    Map<String, Object> register(String username, String password, String confirm, String email);
}
