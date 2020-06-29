package com.sise.service01;

import feign.Feign;

public class HelloMain {
    public static void main(String[] args) {
        HelloClient helloClient = Feign.builder().target(HelloClient.class, "http://localhost:8085/");
        System.out.println(helloClient.sayHello());
    }
}
