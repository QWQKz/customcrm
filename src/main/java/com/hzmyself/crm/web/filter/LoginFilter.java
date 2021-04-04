package com.hzmyself.crm.web.filter;

import com.hzmyself.crm.settings.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getServletPath();
        //对登录页、登录请求 放行
        if("/login.jsp".equals(path)||"/user/login.do".equals(path)){
            filterChain.doFilter(req,resp);
        }else{
            User user = (User) req.getSession().getAttribute("user");
            if(user!=null){
                filterChain.doFilter(req,resp);
            }else{
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
