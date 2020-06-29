package com.sise.service02;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sise.service02.feign.PersonClient;
import org.springframework.stereotype.Component;

@Component
public class CacheService {
    //@CacheResult注解表示被修饰方法返回结果将会被缓存，需要与@HystrixCommand一起使用。
    @CacheResult
    @HystrixCommand
    public PersonClient.Person getPerson(Integer id){
        System.out.println("执行getPerson方法");
        PersonClient.Person person = new PersonClient.Person();
        person.setId(id);
        person.setName("Dony");
        person.setMessages("success");
        person.setAge(21);
        return person;
    }

    @CacheResult
    @HystrixCommand(commandKey = "removeKey")
    public String cacheMethod(String name){
        System.out.println("执行cacheMethod方法");
        return "hello";
    }

    //@CacheRemove注解表示被修饰方法不使用缓存，缓存失效。
    //需要与@CacheResult的缓存key关联。
    @CacheRemove(commandKey = "removeKey")
    @HystrixCommand
    public String updateMethod(String name){
        return "update";
    }

}
