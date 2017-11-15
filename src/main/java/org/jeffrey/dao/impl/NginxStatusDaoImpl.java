package org.jeffrey.dao.impl;


import org.jeffrey.base.BaseRedisGeneratorDao;
import org.jeffrey.dao.NginxStatusDao;
import org.jeffrey.model.NginxStatus;
import org.jeffrey.util.KeyUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * nginxstatusDao实现类
 * @author Jeffrey
 */
@Repository(value = "nginxStatusDao")
public class NginxStatusDaoImpl extends BaseRedisGeneratorDao<String,Integer> implements NginxStatusDao {
    @Override
    public void add(final NginxStatus nginxStatus) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(KeyUtils.nginxStatus(nginxStatus.getDateTime()),nginxStatus.getActiveConnections()+"");
    }

}
