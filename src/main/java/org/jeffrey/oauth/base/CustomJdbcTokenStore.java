package org.jeffrey.oauth.base;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

import static org.jeffrey.oauth.util.CacheConstants.ACCESS_TOKEN_CACHE;
import static org.jeffrey.oauth.util.CacheConstants.REFRESH_TOKEN_CACHE;

/**
 * 扩展默认的 TokenStore, 增加对缓存的支持
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 15:02
 **/
public class CustomJdbcTokenStore extends JdbcTokenStore {

    public CustomJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }

    @Cacheable(value = ACCESS_TOKEN_CACHE,key = "#tokenValue")
    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return super.readAccessToken(tokenValue);
    }

    @CacheEvict(value = ACCESS_TOKEN_CACHE,key = "#tokenValue")
    @Override
    public void removeAccessToken(String tokenValue) {
        super.removeAccessToken(tokenValue);
    }

    @Cacheable(value = REFRESH_TOKEN_CACHE,key = "#token")
    @Override
    public OAuth2RefreshToken readRefreshToken(String token) {
        return super.readRefreshToken(token);
    }

    @CacheEvict(value = REFRESH_TOKEN_CACHE,key = "#token")
    @Override
    public void removeRefreshToken(String token) {
        super.removeRefreshToken(token);
    }
}
