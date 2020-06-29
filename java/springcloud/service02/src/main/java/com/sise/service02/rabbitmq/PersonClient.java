package com.sise.service02.rabbitmq;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public interface PersonClient {

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

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement
    class Student{
        @XmlElement
        String sid;
        @XmlElement
        String sname;
        @XmlElement
        int rchinese;
        @XmlElement
        int rmath;
        @XmlElement
        int renglish;


    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement
    class ClassSchool{
        @XmlElement
        String cid;
        @XmlElement
        String cname;
    }

}
