package com.sise.db.jpa;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/save")
    public Map<String, Object>save(@RequestBody User user){
        userService.save(user);
        Map<String, Object> p = new HashMap<>();
        p.put("code", "success");
        return p;
    }

    @GetMapping(value = "/stuinfo/{uid}")
    @ResponseBody
//    @RequestMapping(value = "/stuinfo/{uid}", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
    public User selectUser(@PathVariable(value = "uid") String uid) throws InterruptedException {
        //Thread.sleep(11000);
        return userService.selectByKey(uid);
    }

}
