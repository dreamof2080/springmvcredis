package org.jeffrey.dao.impl;

import org.jeffrey.base.BaseRedisGeneratorDao;
import org.jeffrey.dao.UserDao;
import org.jeffrey.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2017-11-12.
 */
@Repository(value="userDao")
public class UserDaoImpl extends BaseRedisGeneratorDao<String,User> implements UserDao{
  @Override
  public boolean add(final User user) {
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
        RedisSerializer<String> serializer = getRedisSerializer();
        byte[] key = serializer.serialize(user.getId());
        byte[] name = serializer.serialize(user.getName());
        return redisConnection.setNX(key,name);
      }
    });
    return result;
  }

  @Override
  public boolean add(final List<User> list) {
    Assert.notEmpty(list,"不能为空");
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
        RedisSerializer<String> serializer = getRedisSerializer();
        for(User user:list){
          byte[] key = serializer.serialize(user.getId());
          byte[] name = serializer.serialize(user.getName());
          redisConnection.setNX(key,name);
        }
        return true;
      }
    },false,true);
    return result;
  }

  @Override
  public void delete(String key) {
    List<String> list = new ArrayList<String>();
    list.add(key);
    delete(list);
  }

  public void delete(List<String> keys){
    redisTemplate.delete(keys);
  }

  @Override
  public boolean update(final User user){
    String key = user.getId();
    if(get(key)==null){
      throw new NullPointerException("数据行不存在，key="+key);
    }
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
        RedisSerializer<String> serializer = getRedisSerializer();
        byte[] key = serializer.serialize(user.getId());
        byte[] name = serializer.serialize(user.getName());
        redisConnection.set(key,name);
        return true;
      }
    });
    return result;
  }

  @Override
  public User get(final String keyId) {
    User user = redisTemplate.execute(new RedisCallback<User>() {
      @Override
      public User doInRedis(RedisConnection redisConnection) throws DataAccessException {
        RedisSerializer<String> serializer = getRedisSerializer();
        byte[] key = serializer.serialize(keyId);
        byte[] value = redisConnection.get(key);
        if(value == null){
          return null;
        }
        String name = serializer.deserialize(value);
        return new User(keyId,name);
      }
    });
    return user;
  }
}
