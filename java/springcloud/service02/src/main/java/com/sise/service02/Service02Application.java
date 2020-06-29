package com.sise.service02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient只适用于Eureka作为注册中心
//@EnableDiscoveryClient 可以是其他注册中心。
@EnableDiscoveryClient
//在服务调用者的启动类中打开Feign开关
@EnableFeignClients
//在启动类中加入断路器的注解@EnableCircuitBreaker
@EnableCircuitBreaker
@ServletComponentScan
//Hystrix监控
@EnableHystrixDashboard
public class Service02Application {

    public static void main(String[] args) {
        SpringApplication.run(Service02Application.class, args);
    }

}
