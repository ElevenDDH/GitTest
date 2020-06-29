package com.sise.service01;

import com.sise.service01.rabbitmq.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class Service01Controller {
    @Autowired
    SendService sendService;

//    @Autowired
//    private UserClient userClient;

//    @RequestMapping(value = "/person/{person_id}", method = RequestMethod.GET)
//    public String find_name(@PathVariable("person_id") Integer person_id, HttpServletRequest request){
//        Person person = new Person(person_id, "Dony", 20);
//        person.setMessage(request.getRequestURL().toString());
//
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("persion_id", person.getPersonid().toString());
//        hashMap.put("p_name", person.getPname());
//        hashMap.put("p_age", Integer.toString(person.getPage()));
//        hashMap.put("message", person.getMessage());
//
//        String string = JSON.toJSONString(hashMap);
//        return string;
//    }

    //使用CXF调用REST服务
    @RequestMapping(value = "/person/{person_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonClient.Person find_name(@PathVariable("person_id") Integer person_id, HttpServletRequest request){
        PersonClient.Person person = new PersonClient.Person();
        person.setId(person_id);
        person.setName("Downey");
        person.setAge(21);
        person.setMessages(request.getRequestURL().toString());

        return person;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    //@ResponseBody
    public String hello1() throws Exception{
        Thread.sleep(800);
        return "Hello Spring Cloud!";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    //consume是：指定请求内容类型；
    //Produces是：指定返回内容类型。
    @RequestMapping(value = "/person/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createPerson(@RequestBody PersonClient.Person person){
        System.out.println(person.name+"-"+person.age);
        return "Success, Person Id:" + person.id;
    }

    @RequestMapping(value = "/person/createXML", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_XML_VALUE,
    produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String createXMLP(@RequestBody PersonClient.Person person){
        System.out.println(person.getName()+"-"+person.getId());
        return "<result><message>success</message></result>";
    }


    @RequestMapping(value = "/stu/{stu_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonClient.Stu find_stu_name(@PathVariable("stu_id") Integer stu_id, HttpServletRequest request){
        PersonClient.Stu stu = new PersonClient.Stu();
        stu.sid = stu_id;
        stu.sname = "TOMMY";
        stu.chinese = 89;
        stu.math = 80;
        stu.english = 75;

        return stu;
    }

    @RequestMapping(value = "/stu/sum", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Integer sum(@RequestBody PersonClient.Stu stu){
        Integer all = stu.chinese + stu.math + stu.english;
        System.out.println(all);
        return all;
    }

    @RequestMapping(value = "/stu/createstu", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String createstu(@RequestBody PersonClient.Stu stu){
        Integer average = (stu.chinese + stu.math + stu.english) / 3;
        System.out.println(average);
        return "<result><message>" + average.toString() + "</message></result>";
    }

    //消息消费者
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendRequest(){
        //创建消息
        @SuppressWarnings("rawtypes")
        Message message = MessageBuilder.withPayload("Hello RabbitMQ".getBytes()).build();
        //发送消息
        sendService.sendOrder().send(message);
        return "SUCCESS";
    }

//    //综合实验
//    @RequestMapping(value = "/send/{uid}", method = RequestMethod.GET)
//    public String sendUserRequest(@PathVariable("uid") String uid) {
//        PersonClient.User p = userClient.getPerson(uid);
//        System.out.println("======================p=================" + p);
//        String info = p.uid + "  " + p.username + "  " + p.password;
//        System.out.println("======================info=================" + info);
//
////        User p = userClient.getPerson(uid);
////        System.out.println("======================p=================" + p);
////        String info = p.getUid() + "  " + p.getUsername() + "  " + p.getPassword();
////        System.out.println("======================info=================" + info);
////
////        Message message = MessageBuilder.withPayload(info.getBytes()).build();
////        sendService.sendOrder().send(message);
//
//        return "Success";
//    }

}
