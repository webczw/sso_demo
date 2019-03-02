package com.webczw.sso.controller;

import com.webczw.sso.utils.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.MapUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wilber
 */
@Controller
public class SsoController {

    @RequestMapping("/preLogin")
    public String preLogin(String url, HttpServletRequest request,Model model){
       HttpSession session = request.getSession(false);
        if(session == null){
            model.addAttribute("url",url);
            model.addAttribute("name",url);
            return "login";
        }else{
            String token = (String)session.getAttribute("token");
            return "redirect:http://"+url+"?token="+token;
        }
    }

    @RequestMapping("/login")
    public String login(String username, String password,String url, HttpServletRequest request){
        if("wilber".equals(username) && "123456".equals(password)){
            Map<String,String> map = new HashMap<>(2);
            map.put("username",username);
            map.put("url",url);
            String token = JwtUtils.genToken(map);
            request.getSession().setAttribute("token",token);
            return "redirect:http://"+url+"?token="+token;
        }else{
            return "login";
        }
    }
    @RequestMapping("/checkToken")
    @ResponseBody
    public String checkToken(String token){
        Map<String,String> map = JwtUtils.verifyToken(token);
        if(!MapUtils.isEmpty(map)){
            return "correct";
        }
        return "incorrect";
    }
}
