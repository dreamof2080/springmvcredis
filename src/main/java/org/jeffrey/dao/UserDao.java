package org.jeffrey.dao;

import org.jeffrey.model.User;

import java.util.List;

/**
 * Created by Jeffrey on 2017-11-12.
 */
public interface UserDao {
  /**
   * 添加对象
   * @param user
   * @return
   */
  boolean add(User user);

  /**
   * 添加集合
   * @param list
   * @return
   */
  boolean add(List<User> list);

  /**
   * 删除对象
   * @param key
   */
  void delete(String key);

  /**
   * 获取对象
   * @param keyId
   * @return
   */
  User get(String keyId);

  /**
   * 修改对象
   * @param user
   * @return
   */
  boolean update(User user);
}
