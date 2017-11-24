package org.jeffrey.util;

/**
 * 一些常量和固定值的处理
 * @author Jeffrey
 */
public abstract class KeyUtil {
    static final String UID = "nginxStatus:";
    public static final String LOGIN_NAME_ERROR = "error_name";
    public static final String LOGIN_PASSWORD_ERROR = "error_password";

    public static String nginxStatus(String id){
        return UID + id;
    }
}
