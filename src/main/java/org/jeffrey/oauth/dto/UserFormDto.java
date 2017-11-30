package org.jeffrey.oauth.dto;

import org.jeffrey.oauth.model.User;
import org.jeffrey.oauth.util.PasswordHandler;
import org.jeffrey.oauth.util.Privilege;

/**
 * @author Jeffrey.Liu
 * @create 2017-11-30 17:57
 **/
public class UserFormDto extends UserDto{
    private static final long serialVersionUID = 7959857016962260738L;

    private String password;

    public Privilege[] getAllPrivileges(){
        return new Privilege[]{Privilege.MOBILE,Privilege.UNITY};
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User newUser(){
        final User user = new User().userName(getUserName()).phone(getPhone()).email(getEmail()).password(PasswordHandler.md5(getPassword()));
        user.getPrivileges().addAll(getPrivileges());
        return user;
    }
}
