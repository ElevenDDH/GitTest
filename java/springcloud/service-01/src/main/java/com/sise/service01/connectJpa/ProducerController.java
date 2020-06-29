//package com.sise.service01.connectJpa;
//
//import com.sise.service01.PersonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ProducerController {
//    @Autowired
//    private UserClient userClient;
//
//    //综合实验
//    @RequestMapping(value = "/send/{uid}", method = RequestMethod.GET)
//    public String sendUserRequest(@PathVariable("uid") String uid){
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
//}
