package com.sise.service02.feign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//自定义注解
public @interface MyUrl {
    //定义注解的url与method属性
    String url();
    String method();
}
