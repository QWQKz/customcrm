package com.hzmyself.crm.utils;

import com.hzmyself.crm.exception.ParamException;
import com.hzmyself.crm.exception.PermissionException;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SpringExceptionResolver {

    @ExceptionHandler(value = PermissionException.class)
    public String doLoginException(HttpServletResponse response,Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("success",false);
        map.put("msg",e.getMessage());
        String json = PrintJson.printJsonObj(response,map);
        return json;
    }
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
//        String url = request.getRequestURL().toString();
//        ModelAndView mv = new ModelAndView();
//        String defaultMsg = "System error";
//        Map<String, Object> result = new HashMap<>();
//        // 这里我们要求项目中所有请求json数据，都使用.json结尾
//        if (url.endsWith(".do")) {
//            if(ex instanceof PermissionException || ex instanceof ParamException) {
//                System.out.println("异常处理器开始执行=================================================");
//                result.put("result", ex.getMessage());
//                mv = new ModelAndView("jsonView", result);
//            } else {
////                log.error("unknown json exception, url:" + url, ex);
////                JsonData result = JsonData.fail(defaultMsg);
//                result.put("success", request.getSession().getAttribute("user"));
//                mv = new ModelAndView("jsonView", result);
//            }
//        }
////        else if (url.endsWith(".page")){ // 这里我们要求项目中所有请求page页面，都使用.page结尾
////            log.error("unknown page exception, url:" + url, ex);
////            JsonData result = JsonData.fail(defaultMsg);
////            mv = new ModelAndView("exception", result.toMap());
////        } else {
////            log.error("unknow exception, url:" + url, ex);
////            JsonData result = JsonData.fail(defaultMsg);
////            mv = new ModelAndView("jsonView", result.toMap());
////        }
//        return mv;
//    }
}
