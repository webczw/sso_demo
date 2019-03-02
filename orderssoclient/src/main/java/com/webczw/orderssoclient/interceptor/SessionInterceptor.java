package com.webczw.orderssoclient.interceptor;

import com.webczw.orderssoclient.utils.HttpUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute("login").equals("login")){
            return true;
        }
        String token = request.getParameter("token");
        if(!StringUtils.isEmpty(token)){
            String reqUrl = "http://www.sso.com:5022/sso/checkToken?token="+token;
            byte[] content = HttpUtils.doGet(reqUrl);
            String result = new String(content);
            if("correct".equals(result)){
                request.getSession().setAttribute("login","login");
                return true;
            }
        }
        response.sendRedirect("http://www.sso.com:5022/sso/preLogin?url=www.order.com:5024/order/test/wel");
        return false;
    }
}
