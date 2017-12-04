package org.jeffrey.nginx.service;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.nginx.dao.NginxStatusDao;
import org.jeffrey.nginx.model.NginxStatus;
import org.jeffrey.task.action.NginxStatusAction;
import org.jeffrey.util.HttpRequestUtil;
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
   * 获取nginx连接数信息
   * @param url
   * @return
   */
  public JSONObject getStatus(String url){
    JSONObject jsonObject = HttpRequestUtil.httpGetNginxRequestNums(url);
    String date = jsonObject.getString("date");
    String time = jsonObject.getString("time");
    String total = jsonObject.getString("total");
    NginxStatus nginxStatus = new NginxStatus(date+" "+time,Integer.parseInt(total));
    this.add(nginxStatus);
    return jsonObject;
  }

  /**
   * 从定时任务中获取nginx连接数
   * @return
   */
  public JSONObject getStatusByTask(){
    return NginxStatusAction.jsonObject;
  }

  /**
   * 获取某个区间内的连接数信息
   * @param beginDate
   * @param endDate
   * @return
   */
  public JSONObject getStatusList(String beginDate,String endDate){
    return nginxStatusDao.getNginxStatusList(beginDate,endDate);
  }

  /**
   * 向redis中插入nginx连接数信息
   * @param nginxStatus
   */
  public void add(NginxStatus nginxStatus){
    nginxStatusDao.add(nginxStatus);
  }


}
