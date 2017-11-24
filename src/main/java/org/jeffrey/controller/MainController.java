package org.jeffrey.controller;

import com.alibaba.fastjson.JSONObject;
import org.jeffrey.base.BaseMultiController;
import org.jeffrey.util.KeyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


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


    /** 进入首页 **/
    @RequestMapping(value = "/index")
    public ModelAndView signIn(HttpSession httpSession){
        Object object = httpSession.getAttribute("userName");
        if(object!=null){
            return toView("/main/index",null);
        }else{
            return toView("/login/login",null);
        }
    }

    /** 登陆验证 **/
    @ResponseBody
    @RequestMapping(value = "/loginCheck",produces="text/html;charset=UTF-8")
    public String loginCheck(@RequestParam("userName") String userName, @RequestParam("password") String password,HttpSession httpSession){
        JSONObject jsonObject = new JSONObject();
        if(!"admin".equals(userName)){
            jsonObject.put("msg", KeyUtil.LOGIN_NAME_ERROR);
        }else if(!"1".equals(password)){
            jsonObject.put("msg",KeyUtil.LOGIN_PASSWORD_ERROR);
        }else{
            httpSession.setAttribute("userName",userName);
            jsonObject.put("msg","ok");
        }
        return JSONObject.toJSONString(jsonObject);
    }
}
