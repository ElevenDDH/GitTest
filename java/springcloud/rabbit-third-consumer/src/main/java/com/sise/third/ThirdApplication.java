package com.sise.third;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Comparator;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(ReceiveService.class)
public class ThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdApplication.class, args);
    }

    @StreamListener("myInput")
    public void receive(byte[] msg){
        /**
         * C服务：以JSON格式分别输出某班级中
         * 语文、数学、英语最高分的学生信息
         */
        List<PersonClient.Student> students = JSON.parseArray(new String(msg), PersonClient.Student.class);

        PersonClient.Student maxChinese = students.stream().max(Comparator.comparing(PersonClient.Student::getRchinese)).get();
        PersonClient.Student maxMath = students.stream().max(Comparator.comparing(PersonClient.Student::getRmath)).get();
        PersonClient.Student maxEnglish = students.stream().max(Comparator.comparing(PersonClient.Student::getRenglish)).get();

        String maxChineseJson = JSON.toJSONString(maxChinese);
        String maxMathJson = JSON.toJSONString(maxMath);
        String maxEnglishJson = JSON.toJSONString(maxEnglish);

        System.out.println("groupB Third Consumer 接收到消息：");
        System.out.println("语文最高分\n" + maxChineseJson);
        System.out.println("数学最高分\n" + maxMathJson);
        System.out.println("英语最高分\n" + maxEnglishJson);

    }
}
