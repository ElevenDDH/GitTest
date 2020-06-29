package com.sise.producer.connectJpa;

import com.alibaba.fastjson.JSON;
import com.sise.producer.rabbitmq.SendService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@RestController
public class ProducerController {
    @Autowired(required = true)
    private UserClient userClient;

    @Autowired
    SendService sendService;

    //综合实验
    @RequestMapping(value = "/send/{cid}", method = RequestMethod.GET)
    public String sendUserRequest(@PathVariable("cid") String cid, HttpServletRequest request) throws IOException {
        List<PersonClient.Student> p = userClient.getStudents(cid);

//        String info = p.uid + "  " + p.username + "  " + p.password;
        String json = JSON.toJSONString(p);

        Message message = MessageBuilder.withPayload(json.getBytes()).build();
        sendService.sendOrder().send(message);

        return "Success";
    }
    @RequestMapping(value = "/person/send/{sid}", method = RequestMethod.GET)
    public String sendPersonUserRequest(@PathVariable("sid") String sid) throws Exception {
        PersonClient.Student student = userClient.getPerson(sid);
        String info = "sid:" + student.sid + "%20"
                + "sname:" + student.sname + "%20"
                + "rchinese:" + student.rchinese + "%20"
                + "rmath:" + student.rmath + "%20"
                + "renglish:" + student.renglish;
//        String info = JSON.toJSONString(student);

//        User p = userClient.getPerson(uid);
//        System.out.println("======================p=================" + p);
//        String info = p.getUid() + "  " + p.getUsername() + "  " + p.getPassword();
//        System.out.println("======================info=================" + info);

        //利用java发送http请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8082/togroupA/show/" + info);
        CloseableHttpResponse response1 = httpClient.execute(httpGet);

        return EntityUtils.toString(response1.getEntity(), "UTF-8");
    }
}
