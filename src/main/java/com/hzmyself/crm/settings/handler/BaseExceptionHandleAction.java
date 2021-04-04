package com.hzmyself.crm.settings.handler;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzmyself.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


public class BaseExceptionHandleAction {


    /** 基于@ExceptionHandler异常处理 */
    /*@ExceptionHandler
    public ModelAndView  handleAndReturnPage(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        ModelAndView  mv = new ModelAndView("Exception") ;
        mv.addObject("ex", ex);

        // 根据不同错误转向不同页面
        if (ex instanceof BusinessException) {
            return mv;
        } else {
            return mv; //返回Exception.jsp页面
        }
    }*/

    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object>  handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Map<String, Object> data = new HashMap<String, Object>();
        if(ex instanceof LoginException) {
            LoginException e = (LoginException)ex;
            data.put("success",false);
            data.put("msg", e.getMessage());
            //        data.put("excetion", ex.getClass().getSimpleName());  异常类型
        }
        return data;
    }

}