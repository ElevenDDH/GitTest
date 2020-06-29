package com.sise.service02;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloFHClient helloFHClient;

    @RequestMapping(value = "/feign/hello", method = RequestMethod.GET)
    public String feignHello(){
        //hello方法回超时
        String helloResult = helloFHClient.hello();
        //获取断路器
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
                .getInstance(HystrixCommandKey.Factory
                        .asKey("HelloFHClient#hello()"));
        System.out.println("断路器的状态："  + breaker.isOpen());
        return helloResult;
    }
}
