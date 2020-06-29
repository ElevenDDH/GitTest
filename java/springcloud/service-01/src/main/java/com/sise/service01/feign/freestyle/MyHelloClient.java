package com.sise.service01.feign.freestyle;

public interface MyHelloClient {
    @MyUrl(method = "GET", url = "/hello")
    String myHello();
}
