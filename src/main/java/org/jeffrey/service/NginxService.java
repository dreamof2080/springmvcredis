package org.jeffrey.service;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.util.HttpRequestUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-11-13.
 * nginx操作服务类
 */
@Service
public class NginxService {

  /**
   * 获取nginx的status相关数据
   * @param url
   * @return
   */
  public JSONObject getStatus(String url){
    return HttpRequestUtils.httpGetNginxRequestNums(url);
  }
}
