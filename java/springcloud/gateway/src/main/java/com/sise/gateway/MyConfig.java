package com.sise.gateway;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    /**
     * PatternServiceRouteMapper实例的构造方法：
     * 第一个参数：serviceId的正则表达式；
     * 第二个参数：路由的path
     */
//    @Bean
//    public PatternServiceRouteMapper patternServiceRouteMapper(){
//        return new PatternServiceRouteMapper(
//                "(zuul)-(?<module>.+)-(service)",
//                "${module}/**"
//        );
//    }
}
