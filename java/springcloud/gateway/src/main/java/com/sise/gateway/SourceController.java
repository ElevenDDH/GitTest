package com.sise.gateway;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {

    @RequestMapping(value = "/source/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name){
        return "hello " + name;
    }

    @RequestMapping(value = "/errorTest",method = RequestMethod.GET)
    public String errorTest() throws Exception{
        Thread.sleep(3000);
        return "errorTest";
    }
}
