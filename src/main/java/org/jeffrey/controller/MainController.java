package org.jeffrey.controller;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.base.BaseMultiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 程序入口相关的controller
 *
 * @author Jeffrey.Liu
 * @create 2017-11-21 10:57
 **/
@Controller
@RequestMapping(value = "/main")
public class MainController extends BaseMultiController {
    /** 首页-登录页 **/
    @RequestMapping(value = "/login")
    public ModelAndView login(){
        return toView("/login/login",null);
    }


    /** 登陆 **/
    @RequestMapping(value = "/signIn")
    public ModelAndView signIn(@RequestParam("userName") String userName, @RequestParam("password") String password){
        if("admin".equals(userName) && "1".equals(password)){
            return toView("/main/index",null);
        }else{
            return toView("/login/login",null);
        }
    }

    /** 登陆验证 **/
    @ResponseBody
    @RequestMapping(value = "/loginCheck",produces="text/html;charset=UTF-8")
    public String loginCheck(@RequestParam("userName") String userName, @RequestParam("password") String password){
        JSONObject jsonObject = new JSONObject();
        if(!"admin".equals(userName)){
            jsonObject.put("msg","用户名错误");
        }else if(!"1".equals(password)){
            jsonObject.put("msg","密码错误");
        }else{
            jsonObject.put("msg","ok");
        }
        return JSONObject.toJSONString(jsonObject);
    }
}
