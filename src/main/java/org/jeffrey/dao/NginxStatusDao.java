package org.jeffrey.dao;

import org.jeffrey.model.NginxStatus;

/**
 * nginx访问状态信息Dao
 * @author Jeffrey
 */
public interface NginxStatusDao {
    /** 向redis插入数据 **/
    boolean add(NginxStatus nginxStatus);
}
