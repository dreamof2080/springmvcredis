package org.jeffrey.controller;

import org.jeffrey.base.BaseMultiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    /** 登陆页 **/
    @RequestMapping(value = "/login")
    public ModelAndView test(){
        return toView("/login/login",null);
    }
}
