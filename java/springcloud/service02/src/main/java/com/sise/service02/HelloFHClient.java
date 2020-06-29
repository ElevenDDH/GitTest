package com.sise.service02;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "first-service-provider", fallback = HelloFHClient.HelloClientFallback.class)
public interface HelloFHClient {
    //Feign客户端调用”spring-hystrix-provider”的/hello服务
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello();

    @Component
    static class HelloClientFallback implements HelloFHClient {
        public String hello(){
            return "error hello";
        }
    }
}
