package com.sise.third;

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

        public int getRchinese() {
            return rchinese;
        }

        public void setRchinese(int rchinese) {
            this.rchinese = rchinese;
        }

        public int getRmath() {
            return rmath;
        }

        public void setRmath(int rmath) {
            this.rmath = rmath;
        }

        public int getRenglish() {
            return renglish;
        }

        public void setRenglish(int renglish) {
            this.renglish = renglish;
        }
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
