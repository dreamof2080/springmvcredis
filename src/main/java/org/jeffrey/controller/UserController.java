package org.jeffrey.controller;

import org.jeffrey.base.BaseMultiController;
import org.jeffrey.model.User;
import org.jeffrey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-11-12.
 */
@Controller
@RequestMapping(value = "/userInfo")
public class UserController extends BaseMultiController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/test.do")
  @ResponseBody
  public String test(){
    return "test";
  }

  @RequestMapping(value = "/add/{id}/{name}")
  public ModelAndView add(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id,@PathVariable("name") String name){
    Map<String,Object> map = new HashMap<String, Object>();
    User user = new User(id,name);
    userService.add(user);
    return toView("add",map);
  }

  @RequestMapping(value = {"/{id:\\d+}/query","{id:\\d+}/query.html"},method = {RequestMethod.GET,RequestMethod.PATCH})
  public ModelAndView queryUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id){
    Map<String,Object> map = new HashMap<String, Object>();
    User user = userService.get(id);
    if(user!=null){
      map.put("message","查询ID="+id+"的用户名为"+user.getName());
    }else{
      map.put("message","没有查询到与ID="+id+"相关的数据");
    }
    return toView("message",map);
  }

  @RequestMapping(value = {"/{id:\\d+}/delete","/{id:\\d+}/delete.html"},method = {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") String id){
    Map<String,Object> map = new HashMap<String, Object>();
    try {
      userService.delete(id);
      map.put("message","删除ID为"+id+"的用户成功.");
    }catch (Exception e){
      e.printStackTrace();
      map.put("message","删除ID为"+id+"的用户失败.");
    }
    return toView("message",map);
  }
}
