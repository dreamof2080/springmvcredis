package org.jeffrey.oauth.service.impl;

import org.jeffrey.oauth.dto.UserFormDto;
import org.jeffrey.oauth.dto.UserJsonDto;
import org.jeffrey.oauth.dto.UserOverviewDto;
import org.jeffrey.oauth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 处理用户，账号，安全相关业务
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 18:03
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public UserJsonDto loadCurrentUserJsonDto() {
        return null;
    }

    @Override
    public UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto) {
        return null;
    }

    @Override
    public boolean isExistedUserName(String userName) {
        return false;
    }

    @Override
    public String saveUser(UserFormDto userFormDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
