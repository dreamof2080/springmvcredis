package org.jeffrey.task.action;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.nginx.service.NginxService;
import org.jeffrey.task.TaskAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * nginx定时任务
 *
 * @author Jeffrey.Liu
 * @create 2017-11-17 16:53
 **/
@Component
public class NginxStatusAction implements TaskAction{
    public static JSONObject jsonObject;

    @Autowired
    private NginxService nginxService;

    @Value("${nginxStatus.url}")
    private String url;

    /** 每隔5秒执行 **/
    @Scheduled(cron = "0/5 * * * * ? ")
    @Override
    public void execute(){
        jsonObject = nginxService.getStatus(url);
    }
}
