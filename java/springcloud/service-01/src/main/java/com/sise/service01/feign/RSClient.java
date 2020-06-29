package com.sise.service01.feign;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//使用JAXRS规范来注解
//原HelloClient.java
public interface RSClient {
    @GET
    @Path("/hello")
    public String sayHello();
}
