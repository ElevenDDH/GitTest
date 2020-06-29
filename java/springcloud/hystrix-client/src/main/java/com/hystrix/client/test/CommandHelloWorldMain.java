package com.hystrix.client.test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CommandHelloWorldMain {

    /**
     * 注意：
     * 异步调用使用command.queue().get(timeout, TimeUnit.MILLISECONDS);
     * 同步调用使用command.execute()等同于command.queue.get();
     */

    public static void main(String[] args) throws Exception {
        //每个Command对象只能调用一次，不可以重复调用
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld("Synchronous-hystrix");
        //使用execute()同步调用代码，效果等同于：commandHelloWorld.queue().get();
        String s = commandHelloWorld.execute();
        System.out.println("同步============" + s);

        //异步调用，可以自由控制获取结果的时机
        commandHelloWorld = new CommandHelloWorld("Asynchronous-hystrix");
        Future<String> future = commandHelloWorld.queue();
        //get()操作不能超过command定义的超时时间，默认为1秒
        s = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("异步============" + s);

        System.out.println("主函数===============" + Thread.currentThread().getName());

    }
}
