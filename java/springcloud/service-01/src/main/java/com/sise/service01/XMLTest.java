package com.sise.service01;

import com.sise.service01.PersonClient.Result;


import feign.Feign;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

public class XMLTest {
    public static void main(String[] args) {
        JAXBContextFactory jaxbContextFactory = new JAXBContextFactory.Builder().build();
        PersonClient personClient = Feign.builder()
                .encoder(new JAXBEncoder(jaxbContextFactory))
                .decoder(new JAXBDecoder(jaxbContextFactory))
                .target(PersonClient.class, "http://localhost:8085/");
        PersonClient.Person person = new PersonClient.Person();
        person.id = 10002;
        person.name = "Dony";
        person.age = 20;
        Result result = personClient.createPersonXML(person);
        System.out.println(result.message);//xml


        PersonClient.Stu stu = new PersonClient.Stu();
        stu.sid = 901;
        stu.sname = "TOMMY";
        stu.chinese = 89;
        stu.math = 80;
        stu.english = 75;
        Result average = personClient.createstu(stu);
        System.out.println(average.message);
    }
}
