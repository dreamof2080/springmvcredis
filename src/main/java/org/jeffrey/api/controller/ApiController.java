package org.jeffrey.api.controller;

import org.jeffrey.base.BaseMultiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * api controller
 * @author Jeffrey
 * @date 2017-11-13
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController extends BaseMultiController {

  /** 跳转到oa api页面 **/
  @RequestMapping(value = "/oaapi")
  public ModelAndView status(){
    return toView("/api/oa",null);
  }

  /** 跳转到docker api页面 **/
  @RequestMapping(value = "/dockerapi")
  public ModelAndView getStatus(){
    return toView("/api/docker",null);
  }
}
