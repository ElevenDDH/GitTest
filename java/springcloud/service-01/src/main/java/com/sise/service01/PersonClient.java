package com.sise.service01;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import lombok.Data;

import javax.xml.bind.annotation.*;

public interface PersonClient {
    @RequestLine("GET /person/{personId}")
    Person findById(@Param("personId")Integer personId);
    @RequestLine("GET /stu/{stu_id}")
    Stu findStuById(@Param("stu_id")Integer stu_id);

    //@Headers注解声明请求类型为JSON
    @RequestLine("POST /person/create")
    @Headers("Content-Type: Application/json")
    String createPerson(Person person);
    @RequestLine("POST /stu/sum")
    @Headers("Content-Type: Application/json")
    String sum(Stu stu);

    @RequestLine("POST /person/createXML")
    @Headers("Content-Type: Application/xml")
    Result createPersonXML(Person person);
    @RequestLine("POST /stu/createstu")
    @Headers("Content-Type: Application/xml")
    Result createstu(Stu stu);

    //@Data为属性增加setter/getter方法
//    @Data
//    class Person{
//        Integer personid;
//        String pname;
//        Integer page;
//        String message;
//    }

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

    @Data
    @XmlRootElement
    class Result{
        @XmlElement
        String message;
        @XmlTransient
        public String getMessage() {
            return message;
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement
    class Stu{
        @XmlElement
        Integer sid;
        @XmlElement
        String  sname;
        @XmlElement
        int chinese;
        @XmlElement
        int math;
        @XmlElement
        int english;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement
    class User{
        @XmlElement
        String uid;
        @XmlElement
        String username;
        @XmlElement
        String password;
    }

}
