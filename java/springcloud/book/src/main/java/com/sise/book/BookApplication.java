package com.sise.book;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaClient
public class BookApplication {

    public static void main(String[] args) {
        //SpringApplication.run(BookApplication.class, args);
        System.out.println("请输入端口：");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine();
        new SpringApplicationBuilder(BookApplication.class)
                .properties("server.port=" + port).run(args);
    }

}
