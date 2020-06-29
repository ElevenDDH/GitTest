package com.sise.service02;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class MyInterceptor implements ClientHttpRequestInterceptor {
    //将原来的HttpRequest请求对象转换成自定义
    //的MyHttpRequest.
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        System.out.println("=============这是自定义拦截器实现");
        System.out.println("       原来的URI："+request.getURI());
        //构成新的请求对象
        MyHttpRequest myHttpRequest = new MyHttpRequest(request);
        System.out.println("       拦截后新的URI："+myHttpRequest.getURI());
        return execution.execute(myHttpRequest, body);
    }
}
