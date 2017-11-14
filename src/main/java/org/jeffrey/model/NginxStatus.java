package org.jeffrey.model;

import org.jeffrey.base.BaseModel;

/**
 * nginx访问状态实体类
 */
public class NginxStatus extends BaseModel {

    /** 日期时间 **/
    private String dateTime;
    /** 访问量 **/
    private String activeConnections;

    public NginxStatus(String dateTime,String activeConnections){
        this.dateTime = dateTime;
        this.activeConnections = activeConnections;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(String activeConnections) {
        this.activeConnections = activeConnections;
    }
}
