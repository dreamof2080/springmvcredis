package org.jeffrey.dao.impl;

import org.jeffrey.base.BaseRedisGeneratorDao;
import org.jeffrey.dao.NginxStatusDao;
import org.jeffrey.model.NginxStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

/**
 * nginxstatusDao实现类
 * @author Jeffrey
 */
@Repository(value = "nginxStatusDao")
public class NginxStatusDaoImpl extends BaseRedisGeneratorDao<String,NginxStatus> implements NginxStatusDao {
    @Override
    public boolean add(final NginxStatus nginxStatus) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(nginxStatus.getDateTime());
                byte[] name = serializer.serialize(nginxStatus.getActiveConnections());
                return redisConnection.setNX(key,name);
            }
        });
        return result;
    }
}
