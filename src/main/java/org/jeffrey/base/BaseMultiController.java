package org.jeffrey.base;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Jeffrey on 2017-11-12.
 */
public abstract class BaseMultiController {
  protected ModelAndView toView(final String url, final Map<String,Object> map){
    ModelAndView view = new ModelAndView(url);
    return view;
  }
}
