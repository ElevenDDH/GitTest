package com.sise.rscsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RscsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RscsystemApplication.class, args);
    }

}
