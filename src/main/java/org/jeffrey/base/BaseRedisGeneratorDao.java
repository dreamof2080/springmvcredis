package org.jeffrey.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2017-11-12.
 * @author Jeffrey
 */
public abstract class BaseRedisGeneratorDao<K extends Serializable,V extends Serializable> {
  @Autowired
  protected RedisTemplate<K,V> redisTemplate;

  public void setRedisTemplate(RedisTemplate<K,V> redisTemplate){
    this.redisTemplate = redisTemplate;
  }

  protected RedisSerializer<String> getRedisSerializer(){
    return redisTemplate.getStringSerializer();
  }
}
