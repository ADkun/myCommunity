package com.adkun.myCommunity.dao;

import com.adkun.myCommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 根据用户id查找某个用户
     * @param userId
     * @return
     */
    User selectById(int userId);

}
