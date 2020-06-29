package com.sise.service01.feign;

import com.sise.service01.HelloClient;
import feign.Feign;

public class FeignClientTest {
    public static void main(String[] args) {
        HelloClient helloClient = Feign.builder()
                .client(new MyFeignClient())
                .target(HelloClient.class, "http://localhost:8085/");
        String result = helloClient.sayHello();
        System.out.println("接口响应内容：" + result);
    }
}
