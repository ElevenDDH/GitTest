package com.sise.service02;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sise.service02.feign.PersonClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
public class CollapseService {
    //配置收集1秒内的请求
    @HystrixCollapser(batchMethod = "getPersons", collapserProperties =
            {
                    @HystrixProperty(name = "timerDelayInMilliseconds",
                    value = "1000")
            })
    public Future<PersonClient.Person> getSinglePerson(Integer id){
        System.out.println("执行单个获取的方法");
        return null;
    }

    @HystrixCommand
    public List<PersonClient.Person> getPersons(List<Integer> ids){
        System.out.println("收集请求，参数数量：" + ids.size());
        List<PersonClient.Person> personList = new ArrayList<PersonClient.Person>();
        for (Integer id : ids){
            PersonClient.Person person = new PersonClient.Person();
            person.setId(id);
            person.setName("Dony");
            personList.add(person);
        }
        return personList;
    }
}
