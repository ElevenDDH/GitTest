package com.hystrix.client.switchTest;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

//Hystrix支持缓存功能，当缓存打开后，同一命令的执行直接到缓存获取响应并返回。
public class CacheMain {
    public static void main(String[] args) {
        //初始化请求上下文
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        //请求正常服务
        String key = "cache-key";
        MyCommand command1 = new MyCommand(key);
        MyCommand command2 = new MyCommand(key);
        MyCommand command3 = new MyCommand(key);
        //输出结果
        System.out.println(command1.execute() + "c1是否读取缓存：" + command1.isResponseFromCache());
        System.out.println(command2.execute() + "c2是否读取缓存：" + command2.isResponseFromCache());
        System.out.println(command3.execute() + "c3是否读取缓存：" + command3.isResponseFromCache());
        //获取缓存实例
        HystrixRequestCache cache = HystrixRequestCache.getInstance(
                HystrixCommandKey.Factory.asKey("MyCommandKey"),
                HystrixConcurrencyStrategyDefault.getInstance()
        );
        //清空缓存
        cache.clear(key);
        //重新执行命令
        MyCommand command4 = new MyCommand(key);
        System.out.println(command4.execute() + "c4是否读取缓存：" + command4.isResponseFromCache());
        context.shutdown();
    }

    static class MyCommand extends HystrixCommand<String>{
        private String key;
        public MyCommand(String key){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
            .asKey("ExampleGroup"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("MyCommandKey"))
            );
            this.key = key;
        }

        @Override
        protected String run() throws Exception {
            System.out.println("执行命令");
            return "";
        }

        @Override
        protected String getCacheKey() {
            return this.key;
        }
    }
}
