package com.hystrix.client.switchTest;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class MyCommand extends HystrixCommand<String> {
    int index;

    public MyCommand(int index){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
                .asKey("ExampleGroup")));
        this.index = index;
    }

    protected String run() throws Exception{
        Thread.sleep(500);
        System.out.println("执行方法，当前索引：" + index);
        return "";
    }

    @Override
    protected String getFallback() {
        System.out.println("执行fallback，当前索引：" + index);
        return "";
    }
}
