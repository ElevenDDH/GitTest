package com.sise.service01;

import com.sise.service01.feign.MyEncoder;
import feign.Feign;

public class EncoderTest {
    public static void main(String[] args) {
//        //将请求对象转换成JSON字符串
//        PersonClient personClient = Feign.builder()
//                .encoder(new GsonEncoder())
//                .target(PersonClient.class, "http://localhost:8085/");
//        PersonClient.Person person = new PersonClient.Person();
//        person.id = 1001;
//        person.name = "Downey";
//        person.age = 21;
//        String response = personClient.createPerson(person);
//        System.out.println(response);
//
//        //字符串
//        PersonClient.Stu stu = new PersonClient.Stu();
//        stu.sid = 901;
//        stu.sname = "TOMMY";
//        stu.chinese = 89;
//        stu.math = 80;
//        stu.english = 75;
//        String sum = personClient.sum(stu);
//        System.out.println("成绩总和：" + sum);

        //自定义编码器
        PersonClient personClient = Feign.builder()
                .encoder(new MyEncoder())
                .target(PersonClient.class, "http://localhost:8085/");
        PersonClient.Person person = new PersonClient.Person();
        person.id = 1001;
        person.name = "Downey";
        person.age = 21;
        String response = personClient.createPerson(person);
        System.out.println(response);
    }
}
