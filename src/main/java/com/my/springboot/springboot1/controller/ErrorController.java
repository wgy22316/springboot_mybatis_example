package com.my.springboot.springboot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping(value = "/404")
    public String error_404(){
        return "error/error_404";
    }

    @GetMapping(value = "/500")
    public String error_500(){
        return "error/error_404";
    }
}
