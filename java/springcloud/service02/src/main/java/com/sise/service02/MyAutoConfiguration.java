package com.sise.service02;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//使用自定义拦截器及注解
@Configuration
public class MyAutoConfiguration {
    @Autowired(required = false)
    @MyLoadBalanced
    private List<RestTemplate> templates = Collections.emptyList();
    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateeInitializer(){
        System.out.println("===================这个Bean将在容器初始化时创建");
        return new SmartInitializingSingleton() {
            @Override
            @SuppressWarnings("unchecked")
            public void afterSingletonsInstantiated() {
                for (RestTemplate tpl:templates){
                    //创建一个自定义拦截器实例
                    MyInterceptor myInterceptor = new MyInterceptor();
                    //获取原来的拦截器
                    @SuppressWarnings({"rawtypes"})
                    List list = new ArrayList(tpl.getInterceptors());
                    //将自定义拦截器添加
                    list.add(myInterceptor);
                    //将新的拦截器集合设置到RestTemplate实例
                    tpl.setInterceptors(list);
                }
            }
        };
    }

}
