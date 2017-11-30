package org.jeffrey.oauth.dao.impl;

import org.jeffrey.oauth.dao.UserDao;
import org.jeffrey.oauth.model.User;

import java.util.List;

/**
 * @author Jeffrey.Liu
 * @create 2017-11-30 18:12
 **/
public class UserDaoImpl implements UserDao{
    @Override
    public User findByGuid(String guid) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findUsersByUserName(String userName) {
        return null;
    }
}
