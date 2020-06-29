package com.sise.bookserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BookserverApplication {
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name){
        return "hello " + name;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(BookserverApplication.class).properties(
                "server.port=8090"
        ).run(args);
    }

}
