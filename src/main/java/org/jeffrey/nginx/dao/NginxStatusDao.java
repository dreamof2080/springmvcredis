package org.jeffrey.nginx.dao;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.nginx.model.NginxStatus;

/**
 * nginx访问状态信息Dao
 * @author Jeffrey
 */
public interface NginxStatusDao {
    /**
     * 向redis插入数据
     * @param nginxStatus
     */
    void add(NginxStatus nginxStatus);

    /**
     * 根据日期获取nginxStatus的集合
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    JSONObject getNginxStatusList(String beginDate, String endDate);
}
