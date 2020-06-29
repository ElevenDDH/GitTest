package com.sise.fouter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy //开启对zuul的支持
@SpringBootApplication
public class FouterApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FouterApplication.class).properties(
                "server.port=8089"
        ).run(args);
    }

    //实例化自定义过滤器
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

}
