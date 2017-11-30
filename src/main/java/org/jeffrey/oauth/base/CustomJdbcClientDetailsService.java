package org.jeffrey.oauth.base;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

import static org.jeffrey.oauth.util.CacheConstants.CLIENT_DETAILS_CACHE;

/**
 * 扩展 默认的 ClientDetailsService, 增加逻辑删除判断( archived = 0)
 * SQL中添加  <i>archived = 0</i> 条件
 * 增加缓存支持
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 15:45
 **/
public class CustomJdbcClientDetailsService extends JdbcClientDetailsService{

    private static final String SELECT_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, " +
            "web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove " +
            "from oauth_client_details where client_id = ? and archived = 0 ";

    public CustomJdbcClientDetailsService(DataSource dataSource) {
        super(dataSource);
        setSelectClientDetailsSql(SELECT_CLIENT_DETAILS_SQL);
    }

    @Cacheable(value = CLIENT_DETAILS_CACHE,key = "#clientId")
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }

    @CacheEvict(value = CLIENT_DETAILS_CACHE,key = "#clientDetails.getClientId()")
    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
    }

    @CacheEvict(value = CLIENT_DETAILS_CACHE,key = "#clientId")
    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
    }

    @CacheEvict(value = CLIENT_DETAILS_CACHE,key = "#clientId")
    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
    }
}
