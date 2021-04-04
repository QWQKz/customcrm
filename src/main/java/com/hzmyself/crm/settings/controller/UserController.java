package com.hzmyself.crm.settings.controller;

import com.hzmyself.crm.exception.LoginException;
import com.hzmyself.crm.settings.entity.User;
import com.hzmyself.crm.settings.handler.BaseExceptionHandleAction;
import com.hzmyself.crm.settings.service.UserService;
import com.hzmyself.crm.utils.MD5Util;
import com.hzmyself.crm.utils.PrintJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController extends BaseExceptionHandleAction  {

    @Resource
    private UserService userServicevice;

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public String dologinVerify(HttpServletRequest request, HttpServletResponse response, String loginAct, String loginPwd){
        String json = "";
        //将密码的明文形式转化为密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接受ip地址
        String ip = request.getRemoteAddr();
        System.out.println(ip+"===================");
        User user = null;
        try {
            user = userServicevice.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);
            json = PrintJson.printJsonFlag(response,true);
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
        System.out.println(json+"================================");
        return json;

    }

}
