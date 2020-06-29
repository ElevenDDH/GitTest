//package com.sise.service01.connectJpa;
//
//import com.sise.service01.PersonClient;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@FeignClient("dbService")
//public interface UserClient {
//
//    @RequestMapping(method = RequestMethod.GET, value = "/stuinfo/{uid}")
//    PersonClient.User getPerson(@PathVariable("uid") String uid);
//
////    @RequestMapping(method = RequestMethod.GET, value = "/stuinfo/{uid}")
////    User getPerson(@PathVariable("uid") String uid);
//
//}
