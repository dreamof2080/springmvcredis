package org.jeffrey.service;

import org.jeffrey.dao.UserDao;
import org.jeffrey.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jeffrey on 2017-11-12.
 */
@Service
public class UserService {
  @Resource(name="userDao")
  private UserDao userDao;

  public void setUserDao(UserDao userDao){
    this.userDao = userDao;
  }

  public void add(User user){
    userDao.add(user);
  }

  public void delete(String id){
    userDao.delete(id);
  }

  public User get(String id){
    return userDao.get(id);
  }
}
