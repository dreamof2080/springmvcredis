package org.jeffrey.oauth.base;


import org.jeffrey.oauth.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jeffrey.Liu
 * @create 2017-11-30 18:13
 **/
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setGuid(resultSet.getString("guid"));
        user.setArchived(resultSet.getBoolean("archived"));
        user.setCreateTime(resultSet.getTimestamp("create_time").toLocalDateTime());
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setPassword(resultSet.getString("password"));
        user.setUserName(resultSet.getString("username"));
        user.setLastLoginTime(resultSet.getTimestamp("last_login_time"));
        return user;
    }
}
