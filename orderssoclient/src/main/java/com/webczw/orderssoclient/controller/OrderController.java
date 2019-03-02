package com.webczw.orderssoclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/test")
public class OrderController {
    @RequestMapping("/wel")
    public String wel(){
            return "wel";
        }

}
