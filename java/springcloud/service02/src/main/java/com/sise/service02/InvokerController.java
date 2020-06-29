package com.sise.service02;

import com.netflix.appinfo.InstanceInfo;
import com.sise.service02.feign.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@Configuration
public class InvokerController {
    @Autowired
    private PersonClient personClient;

    @Autowired
    private PersonService personService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CollapseService collapseService;

    //服务查询-健康自检
    @Autowired
    private DiscoveryClient discoveryClient;


    //RestTemplate对象调用 REST服务 处理负载均衡
    @Bean
    //该注解解决RestTemplate对象分布式处理能力
    @LoadBalanced
    //@MyLoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public String router() {
        RestTemplate restTemplate = getRestTemplate();
        //通过服务名称去调用服务
        //String s = restTemplate.getForObject("http://first-service-provider/person/1740128221", String.class);
        String s = restTemplate.getForObject("http://first-service-invoker/hello", String.class);
        return s;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello Dony!";
    }

    @RequestMapping(value = "/router/{personId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonClient.Person router1(@PathVariable Integer personId) {
        PersonClient.Person person = personService.getPerson(personId);
        return person;
    }

    //健康自检
    @RequestMapping(value = "/checksafe", method = RequestMethod.GET)
    public String checksafe() {
        List<ServiceInstance> instances = getServiceInstances();
        for (ServiceInstance serviceInstance : instances) {
            EurekaServiceInstance eurekaServiceInstance = (EurekaServiceInstance) serviceInstance;
            InstanceInfo instanceInfo = eurekaServiceInstance.getInstanceInfo();
            System.out.println(instanceInfo.getAppName() + "==="
                    + instanceInfo.getInstanceId() + "===" + instanceInfo.getStatus());
        }
        return "";
    }

    //实现服务查询-健康自检方法
    private List<ServiceInstance> getServiceInstances() {
        List<String> ids = discoveryClient.getServices();
        List<ServiceInstance> result = new ArrayList<ServiceInstance>();
        for (String id : ids) {
            List<ServiceInstance> instances = discoveryClient.getInstances(id);
            result.addAll(instances);
        }
        return result;
    }


    @RequestMapping(value = "/invokeHello", method = RequestMethod.GET)
    public String invokeHello() {
        return personClient.hello();
    }

    @RequestMapping(value = "/reign_router", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String invoker_router() {
        PersonClient.Person person = personClient.getPerson(2);
        return person.getMessages();
    }

    @RequestMapping(value = "/testContract", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String testContract() {
        String springResult = personClient.springHello();
        System.out.println("使用@RequestMapping注解的返回的结果：" + springResult);
        String myResult = personClient.myHello();
        System.out.println("使用@MyUrl注解的返回的结果：" + myResult);
        return "请查看控制台";
    }

    //测试配置
    @RequestMapping(value = "/testConfig", method = RequestMethod.GET)
    public String testConfig() {
        String result = personService.testConfig();
        return result;
    }

    @RequestMapping(value = "/testException", method = RequestMethod.GET)
    public String testException() {
        String result = personService.testException();
        return result;
    }

    //缓存注解
    @RequestMapping(value = "/cache1/{personId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonClient.Person testCacheResult(@PathVariable Integer personId) {
        //调用多次服务
        for (int i = 0; i < 3; i++) {
            PersonClient.Person person = cacheService.getPerson(personId);
            System.out.println("控制器调用服务：" + i);
        }
        return new PersonClient.Person();
    }

    @RequestMapping(value = "/cache2", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCacheRemove() {
        for (int i = 0; i < 3; i++) {
            cacheService.cacheMethod("a");
            System.out.println("控制器调用服务：" + i);
        }

        cacheService.updateMethod("a");
        System.out.println("=========================缓存失效");

        for (int i = 0; i < 3; i++) {
            cacheService.updateMethod("a");
            cacheService.cacheMethod("a");
            System.out.println("控制器调用服务：" + i);
        }
        return "";
    }

    //============合并请求注解============
    @RequestMapping(value = "/collapse", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCollapse() throws Exception {
        //连续执行三次请求
        Future<PersonClient.Person> future1 = collapseService.getSinglePerson(1);
        Future<PersonClient.Person> future2 = collapseService.getSinglePerson(2);
        Future<PersonClient.Person> future3 = collapseService.getSinglePerson(3);
        PersonClient.Person person1 = future1.get();
        PersonClient.Person person2 = future2.get();
        PersonClient.Person person3 = future3.get();
        System.out.println(person1.getId() + "-----" + person1.getName());
        System.out.println(person2.getId() + "-----" + person2.getName());
        System.out.println(person3.getId() + "-----" + person3.getName());
        return "";
    }

}
