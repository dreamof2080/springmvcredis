package org.jeffrey.oauth.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 处理账号秘密，使用MD5加密
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 18:01
 **/
public abstract class PasswordHandler {
    public static String md5(String password){
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password,null);
    }
}
