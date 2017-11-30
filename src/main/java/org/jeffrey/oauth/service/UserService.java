package org.jeffrey.oauth.service;

import org.jeffrey.oauth.dto.UserFormDto;
import org.jeffrey.oauth.dto.UserJsonDto;
import org.jeffrey.oauth.dto.UserOverviewDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * userService接口
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 16:18
 **/
public interface UserService extends UserDetailsService {
    UserJsonDto loadCurrentUserJsonDto();
    UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto);
    boolean isExistedUserName(String userName);
    String saveUser(UserFormDto userFormDto);
}
