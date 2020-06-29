package com.hystrix.client.switchTest;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandProperties;

//信号量隔离策略示例
public class SemaphoreIso {
    public static void main(String[] args) throws Exception {
        //配置使用信号里的策略进行隔离
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.execution.isolation.strategy",
                HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
        );
        //设置最大并发数，默认值为10，此处设置为2
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests",
                2
        );
        //设置执行回退的方法的最大并发，默认值为10，此处设置为20
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests",
                20
        );
        for (int i =0; i < 6; i++){
            final int index = i;
            Thread thread = new Thread(){
                public void run(){
                    MyCommand command = new MyCommand(index);
                    command.execute();
                }
            };
            thread.start();
        }
        Thread.sleep(5000);
    }
}
