package com.sise.second;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(ReceiveService.class)
public class SecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondApplication.class, args);
    }

    @StreamListener("myInput")
    public void receive(byte[] msg){
        /**
         * B服务：以JSON格式按照成绩
         * 总分排序输出某班级学生信息
         */
        List<PersonClient.Student> students = JSON.parseArray(new String(msg), PersonClient.Student.class);

        Collections.sort(students, new Comparator<PersonClient.Student>() {
            public int compare(PersonClient.Student s1, PersonClient.Student s2) {
                int result = (s1.rchinese + s1.rmath + s1.renglish) - (s2.rchinese + s2.rmath + s2.renglish);
                return result > 0 ? -1 : 1;
            }
        });

        String json = JSON.toJSONString(students);

        System.out.println("groupB Second Consumer 接收到消息：" + new String(json));
    }

}
