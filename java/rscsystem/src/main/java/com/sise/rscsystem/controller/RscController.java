package com.sise.rscsystem.controller;

import com.sise.rscsystem.bean.Postman;
import com.sise.rscsystem.bean.RscUser;
import com.sise.rscsystem.bean.UserRole;
import com.sise.rscsystem.service.PostService;
import com.sise.rscsystem.service.UserRoleService;
import com.sise.rscsystem.service.RscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/rsc")
public class RscController {
    @Autowired
    @Resource
    private RscUserService rscUserService;
    @Autowired
    @Resource
    private UserRoleService userRoleService;
    @Autowired
    @Resource
    private PostService postService;

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }
    @RequestMapping(value = "/client_register")
    public String r(){
        return "client/client_register";
    }
    //执行注册
    @RequestMapping(value = "/clientRegister")
    public String register(@ModelAttribute RscUser rscUser, HttpSession httpSession){
        String name = rscUser.getName();
        //Long userid = rscUserService.findUserByName(name).getId();
        //Long roleid = userRoleService.findByUserid(userid).getRole_id();

        if (rscUserService.findUserByName(name) != null){
            return "fail";
        }else {
            //String userid = newId();
            //rscUser.setId(userid);//全改String
            rscUser.setName(rscUser.getName());
            rscUser.setAccount(rscUser.getAccount());
            String p = new BCryptPasswordEncoder().encode(rscUser.getPassword());
            rscUser.setPassword(p);
            httpSession.setAttribute("client", rscUser);
            rscUserService.register(rscUser);

            RscUser user = rscUserService.findUserByName(name);
            //同时更新user_role
            Long user_id = user.getId();
            UserRole userRole = new UserRole();
            userRole.setUser_id(user_id);
            userRole.setRole_id((long) 3);
            userRoleService.insertRole(userRole);
            return "success";
        }
    }
//    //生成自动随机递增id
//    public String newId(){
//        String id="";
//        //获取当前时间戳
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String temp = sf.format(new Date());
//        //获取随机数
//        int random=(int) ((Math.random()+1)*10);
//        id=temp+random;
//        return id;
//    }

    @RequestMapping(value = "/post_register")
    public String postR(){
        return "post/post_register";
    }
    @RequestMapping(value = "/postRegister")
    public String postRegister(@ModelAttribute RscUser rscUser, @ModelAttribute Postman postman, HttpSession httpSession){
        String name = rscUser.getName();
        if (rscUserService.findUserByName(name) != null){
            return "fail";
        }else {
            postman.setPostman_account(rscUser.getAccount());
            postman.setPostman_area(postman.getPostman_area());
            postman.setActive("0");
            postService.postmanRegister(postman);

            rscUser.setName(rscUser.getName());
            rscUser.setAccount(rscUser.getAccount());
            String p = new BCryptPasswordEncoder().encode(rscUser.getPassword());
            rscUser.setPassword(p);
            rscUserService.register(rscUser);

            return "post/post_register_success";
        }
    }
}
