package org.jeffrey.oauth.dao;

import org.jeffrey.oauth.base.BaseUserDao;
import org.jeffrey.oauth.model.User;

import java.util.List;

public interface UserDao extends BaseUserDao {
    User findByGuid(String guid);

    void saveUser(User user);

    void updateUser(User user);

    User findByUserName(String userName);

    List<User> findUsersByUserName(String userName);
}
