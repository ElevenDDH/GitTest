package com.sise.service02.rabbitmq;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {
    @RequestMapping(value = "/show/{info}", method = RequestMethod.GET)
    public String show(@PathVariable(value = "info") String info) throws InterruptedException {
        //Thread.sleep(11000);
        System.out.println("groupA first Consumer 接收到消息：" + info);
        return "ok";
    }

//    @RequestMapping(value = "/show", method = RequestMethod.GET)
//    public String show1(HttpServletRequest request){
//        System.out.println("groupA first Consumer 接收到消息：" + request.getParameter("info"));
//        return "ok";
//    }
}
