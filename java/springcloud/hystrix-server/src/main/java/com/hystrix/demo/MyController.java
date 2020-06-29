package com.hystrix.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {
    @GetMapping("/normalHello")
    @ResponseBody
    public String normalHello(HttpServletRequest request){
        return "Hello World";
    }

    @GetMapping("/errorHello")
    @ResponseBody
    public String errorHello(HttpServletRequest request) throws Exception{
        Thread.sleep(10000);
        return "Error";
    }
}
