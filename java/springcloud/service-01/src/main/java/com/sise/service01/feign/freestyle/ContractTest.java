package com.sise.service01.feign.freestyle;

import feign.Feign;

public class ContractTest {
    public static void main(String[] args) {
        MyHelloClient helloClient = Feign.builder()
                .contract(new MyContract())
                .target(MyHelloClient.class, "http://localhost:8085/");
        String result = helloClient.myHello();
        System.out.println("======接口响应内容：" + result);
    }
}
