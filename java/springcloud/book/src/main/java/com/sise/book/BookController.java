package com.sise.book;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(@PathVariable Integer bookId){
        Book book = new Book();
        book.setId(bookId);
        book.setName("微服务");
        book.setAuthor("Downey");
        return book;
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPerson(@PathVariable Integer personId){
        Person person = new Person();
        person.setId(personId);
        person.setName("ydy");
        person.setAge(22);
        return person;
    }

}
