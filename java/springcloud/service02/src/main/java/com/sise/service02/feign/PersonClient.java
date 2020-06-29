package com.sise.service02.feign;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@FeignClient("first-service-provider")//声明服务名称
public interface PersonClient {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();

    @RequestMapping(method = RequestMethod.GET, value = "/person/{personId}")
    Person getPerson(@PathVariable("personId") Integer personId);

    @Data
    @XmlRootElement
    class Person{
        @XmlElement
        Integer id;
        @XmlElement
        String name;
        @XmlElement
        Integer age;
        @XmlElement
        String messages;
        @XmlTransient
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        @XmlTransient
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @XmlTransient
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        @XmlTransient
        public String getMessages() {
            return messages;
        }
        public void setMessages(String messages) {
            this.messages = messages;
        }
    }

    @MyUrl(method = "GET", url = "/hello")
    String myHello();

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String springHello();
}
