package com.sise.sale;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SaleApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SaleApplication.class, args);
        System.out.println("请输入端口：");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine();
        new SpringApplicationBuilder(SaleApplication.class)
                .properties("server.port=" + port).run(args);
    }

}
