package org.jeffrey.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 启动应用时执行相关的初始化操作
 * @author Jeffrey
 */
@Service
public class InitService {

    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }
}
