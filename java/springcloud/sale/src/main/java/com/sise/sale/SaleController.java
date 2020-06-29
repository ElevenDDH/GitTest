package com.sise.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/sale-book/{bookId}", method = RequestMethod.GET)
    public String saleBook(@PathVariable Integer bookId){
        Book book = bookService.getBook(bookId);
        System.out.println("销售模块处理销售，要销售图书id：" + book.getId()
        + "，书名：" + book.getName());
        return "SUCCESS";
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.GET)
    public String person(@PathVariable Integer personId){
        Person person = bookService.getPerson(personId);
        System.out.println("person id：" + person.getId()
                + "，person name：" + person.getName());
        return "SUCCESS";
    }

    @RequestMapping(value = "/errorTest",method = RequestMethod.GET)
    public String errorTest() throws Exception{
        Thread.sleep(3000);
        return "errorTest";
    }
}
