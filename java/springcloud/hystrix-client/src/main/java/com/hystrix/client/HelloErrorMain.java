package com.hystrix.client;

public class HelloErrorMain {
    public static void main(String[] args) {
        //默认情况下，如果调用web服务无法在1秒内完成，将会触发回退。

        //请求异常服务
        String normalUrl = "http://localhost:8083/errorHello";
        HelloCommand command = new HelloCommand(normalUrl);
        String  result = command.execute();
        System.out.println("请求异常的服务，结果：" + result);
    }
}
