package com.sise.sale;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("zuul-book-service")//声明调用书本服务
public interface BookService {
    @RequestMapping(method = RequestMethod.GET, value = "/book/{bookId}")
    Book getBook(@PathVariable("bookId") Integer bookId);

    @RequestMapping(method = RequestMethod.GET, value = "/person/{personId}")
    Person getPerson(@PathVariable("personId") Integer personId);
}
