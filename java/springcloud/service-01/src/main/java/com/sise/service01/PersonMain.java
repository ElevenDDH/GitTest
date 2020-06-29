package com.sise.service01;

import com.sise.service01.feign.MyDecoder;
import feign.Feign;

public class PersonMain {
    public static void main(String[] args) {
//        //增加GsonDecoder解码器，会将返回的响应JSON字符串转换为接口方法返回的对象。
//        PersonClient personClient = Feign.builder()
//                .decoder(new GsonDecoder())//json转对象（解码器）
//                .target(PersonClient.class, "http://localhost:8085/");
//        PersonClient.Person person = personClient.findById(2020);
//        System.out.println(person.id);
//        System.out.println(person.name);
//        System.out.println(person.age);
//        System.out.println(person.messages);
//        System.out.println(person);

        //自定义解码器
        PersonClient personClient = Feign.builder()
                .decoder(new MyDecoder())//json转对象（解码器）
                .target(PersonClient.class, "http://localhost:8085/");
        PersonClient.Person person = personClient.findById(2020);
        System.out.println(person.name);
        System.out.println(person.age);
        System.out.println(person.messages);
        System.out.println(person);


//        //增加GsonDecoder解码器，会将返回的响应JSON字符串转换为接口方法返回的对象。
//        PersonClient personClient = Feign.builder()
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())//json转对象（解码器）
//                .target(PersonClient.class, "http://localhost:8085/");
//        //encoder
//        PersonClient.Stu stu = personClient.findStuById(901);
//        System.out.println(stu);//json
//        //decoder
//        String sum = personClient.sum(stu);
//        System.out.println("成绩总和：" + sum);



    }
}
