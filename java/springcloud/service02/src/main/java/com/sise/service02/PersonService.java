package com.sise.service02;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sise.service02.feign.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PersonService {
    @Autowired
    private RestTemplate restTemplate;

    //@HystrixCommand注解修饰方法getPerson,并且配置了回退方法
    @HystrixCommand(fallbackMethod = "getPersonFallback")
    public PersonClient.Person getPerson(Integer id){
        //使用RestTemplate调用Eureka服务
        PersonClient.Person person = restTemplate.getForObject(
                "http://first-service-provider/person/{person_id}",
                PersonClient.Person.class, id
        );
        return person;
    }

    //回退方法，返回一个默认的Person
    public PersonClient.Person getPersonFallback(Integer id){
        PersonClient.Person person = new PersonClient.Person();
        person.setId(0);
        person.setName("回退");
        person.setAge(-1);
        person.setMessages("request error");
        return person;
    }

    //测试配置，超时时间为1000毫秒，设置命令执行的线程池大小为 1
    @HystrixCommand(
            fallbackMethod = "testConfigFallback", groupKey = "MyGroup",
            commandKey = "MyCommandKey", threadPoolKey = "MyCommandPool",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "1000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1")
            }
    )
    public String testConfig(){
        try {
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }
    public String testConfigFallback(){
        return "Timeout-error";
    }

    //如果方法抛出MyException，不会触发回退
    @HystrixCommand(fallbackMethod = "testExceptionFallBack"
//    , ignoreExceptions = {MyException.class}
    )
    public String testException(){
        //throw new MyException();
        return "testException-error";
    }
    public String testExceptionFallBack(){
        return "Exception-error";
    }
}

