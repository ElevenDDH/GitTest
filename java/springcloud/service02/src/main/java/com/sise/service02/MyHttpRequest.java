package com.sise.service02;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import java.net.URI;

//自定义请求类
public class MyHttpRequest implements HttpRequest {
    private HttpRequest sourceRequest;
    public MyHttpRequest(HttpRequest sourceRequest){
        this.sourceRequest = sourceRequest;
    }
    @Override
    public String getMethodValue() {
        return null;
    }

    //对原来请求的URI进行改写
    //所有请求都会转移到/hello
    @Override
    public URI getURI() {
        try {
            String oldUri = sourceRequest.getURI().toString();
            URI newUri = new URI("http://localhost:9000/hello");
            return newUri;
        }catch (Exception e){
            e.printStackTrace();
        }
        return sourceRequest.getURI();
    }

    @Override
    public HttpHeaders getHeaders() {
        return sourceRequest.getHeaders();
    }

    @Override
    public HttpMethod getMethod() {
        return sourceRequest.getMethod();
    }
}
