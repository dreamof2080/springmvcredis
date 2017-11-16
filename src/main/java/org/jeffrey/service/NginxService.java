package org.jeffrey.service;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.dao.NginxStatusDao;
import org.jeffrey.model.NginxStatus;
import org.jeffrey.util.HttpRequestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jeffrey on 2017-11-13.
 * nginx操作服务类
 */
@Service
public class NginxService {

  @Resource(name = "nginxStatusDao")
  private NginxStatusDao nginxStatusDao;

  /**
   * 获取nginx的status相关数据
   * @param url
   * @return
   */
  public JSONObject getStatus(String url){
    JSONObject jsonObject = HttpRequestUtils.httpGetNginxRequestNums(url);
    String date = jsonObject.getString("date");
    String time = jsonObject.getString("time");
    String total = jsonObject.getString("total");
    NginxStatus nginxStatus = new NginxStatus(date+" "+time,Integer.parseInt(total));
    this.add(nginxStatus);
    return jsonObject;
  }

  public JSONObject getStatusList(String beginDate,String endDate){
    return nginxStatusDao.getNginxStatusList(beginDate,endDate);
  }

  private void add(NginxStatus nginxStatus){
    nginxStatusDao.add(nginxStatus);
  }


}
