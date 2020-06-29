package com.sise.service01.feign;

import feign.Feign;
import feign.jaxrs.JAXRSContract;

public class RsMain {
    public static void main(String[] args) {
        //设置JAXRSContract类，feign就知道如何处理JAXRS相关注解
        RSClient rsClient = Feign.builder().contract(new JAXRSContract())
                .target(RSClient.class, "http://localhost:8085/");
        String result = rsClient.sayHello();
        System.out.println("接口响应内容：" + result);
    }
}
