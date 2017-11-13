package org.jeffrey.controller;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.base.BaseMultiController;
import org.jeffrey.service.NginxService;
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

  @RequestMapping(value = "/status.do")
  public ModelAndView status(){
    return toView("/nginx/status",null);
  }

  @ResponseBody
  @RequestMapping(value = "/getStatus")
  public String getStatus(@RequestParam("url") String url){
    JSONObject jsonObject = nginxService.getStatus(url);
    return JSONObject.toJSONString(jsonObject);
  }
}