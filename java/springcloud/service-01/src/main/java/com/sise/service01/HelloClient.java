package com.sise.service01;

import feign.RequestLine;

public interface HelloClient {
    @RequestLine("GET /hello")
    String sayHello();
}
