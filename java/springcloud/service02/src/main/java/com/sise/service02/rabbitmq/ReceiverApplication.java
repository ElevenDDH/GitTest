package com.sise.service02.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.List;
import java.util.stream.Collectors;
//import org.springframework.cloud.stream.messaging.Processor;
/**
 * Spring cloud stream 内置了三个接口：Sink、Source与Processor
 * Processor接口继承Sink与Source。在实际应用中只使用Processor.
 */


@SpringBootApplication
@EnableBinding(ReceiveService.class)
public class ReceiverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }


    @StreamListener("myInput")
    public void receive(byte[] msg){
        /**
         * A服务：以JSON格式按照学号顺序
         * 输出某班级学生信息；
         * 以JSON格式输出某位学生的个人信息
         */
        List<PersonClient.Student> students = JSON.parseArray(new String(msg), PersonClient.Student.class);
        //按学号升序
        students.stream().sorted((s1, s2) -> s1.sid.compareTo(s2.sid)).collect(Collectors.toList());
        String json = JSON.toJSONString(students);

        System.out.println("groupA first Consumer 接收到消息：" + json);
    }
}
