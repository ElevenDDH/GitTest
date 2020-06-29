package com.hystrix.client;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FallbackTest {
    public static void main(String[] args) {
        //测试一下断路器被打开时的回退
        //断路器被强制打开
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.circuitBreaker.forceOpen", "true"
        );
        FallbackCommand command = new FallbackCommand();
        command.execute();

        //断路器关闭
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.circuitBreaker.forceOpen", "false"
        );
        FallbackCommand command2 = new FallbackCommand();
        command2.execute();
    }

    static class FallbackCommand extends HystrixCommand<String>{
        public FallbackCommand(){
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        }

        //断路器被强制打开，该方法不会执行
        @Override
        protected String run() throws Exception {
            System.out.println("命令执行");
            return "";
        }

        //回退方法，断路器打开后会执行回退
        protected String getFallback(){
            System.out.println("执行回退方法");
            return "fallback";
        }
    }

}
