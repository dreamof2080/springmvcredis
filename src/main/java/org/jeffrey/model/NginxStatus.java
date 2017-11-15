package org.jeffrey.model;

import org.jeffrey.base.BaseModel;

/**
 * nginx访问状态实体类
 */
public class NginxStatus extends BaseModel {

    /** 日期时间 **/
    private String dateTime;
    /** 访问量 **/
    private Integer activeConnections;

    public NginxStatus(String dateTime,Integer activeConnections){
        this.dateTime = dateTime;
        this.activeConnections = activeConnections;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(Integer activeConnections) {
        this.activeConnections = activeConnections;
    }
}
