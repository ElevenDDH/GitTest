package com.sise.service01;

import com.sise.service01.rabbitmq.SendService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

import java.util.Scanner;

//import org.springframework.cloud.stream.messaging.Processor;
/**
 * Spring cloud stream 内置了三个接口：Sink、Source与Processor
 * Processor接口继承Sink与Source。在实际应用中只使用Processor.
 */


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@EnableBinding表示容器启动时，会自动绑定 SendService 接口中定义通道
@EnableBinding(SendService.class)
//@EnableBinding(value = {SendService.class, Processor.class})
public class Service01Application {

    public static void main(String[] args) {
        //成为两次启动服务发布者实例，避免端口冲突，增加从控制台读取端口号来启动服务
        System.out.println("请输入端口：");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine();
        new SpringApplicationBuilder(Service01Application.class)
                .properties("server.port=" + port).run(args);
        //SpringApplication.run(Service01Application.class, args);
    }

}
