package org.jeffrey.util;

/**
 * redis的key工具类
 * @author Jeffrey
 */
public abstract class KeyUtils {
    static final String UID = "nginxStatus:";

    public static String nginxStatus(String id){
        return UID + id;
    }
}
