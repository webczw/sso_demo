package com.webczw.userssoclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wilber
 */
@Controller
@RequestMapping("/test")
public class UserController {
    @RequestMapping("/wel")
    public String wel(){
        return "wel";
    }
}
