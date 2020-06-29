package com.sise.producer.connectJpa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "dbService", fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/studentinfo/{sid}")
    PersonClient.Student getPerson(@PathVariable("sid") String sid);

    @RequestMapping(method = RequestMethod.GET, value = "/studentsinfo/{cid}")
    List<PersonClient.Student> getStudents(@PathVariable("cid") String cid);

//    @RequestMapping(method = RequestMethod.GET, value = "/stuinfo/{uid}")
//    User getPerson(@PathVariable("uid") String uid);

    @Component
    static class UserClientFallback implements UserClient {

        @Override
        public PersonClient.Student getPerson(String uid) {
            System.out.println("Person error, fallback");
            return null;
        }

        @Override
        public List<PersonClient.Student> getStudents(String cid) {
            System.out.println("Students error, fallback");
            return null;
        }

    }
}
