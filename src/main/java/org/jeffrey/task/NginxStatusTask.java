package org.jeffrey.task;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.service.NginxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * nginx定时任务
 *
 * @author Jeffrey.Liu
 * @create 2017-11-17 16:53
 **/
@Component
public class NginxStatusTask {
    public static JSONObject jsonObject;

    @Autowired
    private NginxService nginxService;

    /** 每隔5秒执行 **/
    @Scheduled(cron = "0/5 * * * * ? ")
    public void execute(){
        // TODO: 2017-11-17 url修改为从配置文件中读取 
        jsonObject = nginxService.getStatus("http://192.168.0.231:8088/status");
    }
}
