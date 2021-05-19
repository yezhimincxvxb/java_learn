package com.yzm.mysql.mapper;

import com.yzm.mysql.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findById(Integer id);

    boolean updateUser(User user);
}
