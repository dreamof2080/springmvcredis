package org.jeffrey.nginx.controller;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.base.BaseMultiController;
import org.jeffrey.nginx.service.NginxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Jeffrey on 2017-11-13.
 */
@Controller
@RequestMapping(value = "/nginxInfo")
public class NginxController extends BaseMultiController {
  @Autowired
  private NginxService nginxService;

  /** 测试用 **/
  @RequestMapping(value = "/test")
  public ModelAndView test(){
    return toView("/nginx/test",null);
  }

  /** 跳转到status页面，用echarts监控目前nginx连接数的情况 **/
  @RequestMapping(value = "/status")
  public ModelAndView status(){
    return toView("/nginx/status",null);
  }

  /** 获取nginx连接数 **/
  @ResponseBody
  @RequestMapping(value = "/getStatus")
  public String getStatus(){
    JSONObject jsonObject = nginxService.getStatusByTask();
    return JSONObject.toJSONString(jsonObject);
  }

  /** 获取某个区间内的连接数 **/
  @ResponseBody
  @RequestMapping(value = "/getStatusList")
  public String getStatusList(@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate){
    JSONObject jsonObject = nginxService.getStatusList(beginDate,endDate);
    return JSONObject.toJSONString(jsonObject);
  }
}
