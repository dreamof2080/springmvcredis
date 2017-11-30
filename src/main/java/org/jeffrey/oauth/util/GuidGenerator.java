package org.jeffrey.oauth.util;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

import java.util.UUID;

/**
 * 随机数生成处理工具
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 16:38
 **/
public abstract class GuidGenerator {
    private static RandomValueStringGenerator defaultClientSecretGenerator = new RandomValueStringGenerator(32);

    public static String generate(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String generateClientSecret(){
        return defaultClientSecretGenerator.generate();
    }
}
