package com.hystrix.client.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String> {
    private final String name;

    public CommandHelloWorld(String name){
        //最少配置：指定命令组名
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //依赖逻辑封装在run()方法中
        return "Hello" + name + "! Thread:" + Thread.currentThread().getName();
    }
}
