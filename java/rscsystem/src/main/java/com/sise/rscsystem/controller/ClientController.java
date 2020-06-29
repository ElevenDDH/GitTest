package com.sise.rscsystem.controller;

import com.sise.rscsystem.bean.Mail;
import com.sise.rscsystem.bean.Postman;
import com.sise.rscsystem.bean.RscUser;
import com.sise.rscsystem.service.MailService;
import com.sise.rscsystem.service.PostService;
import com.sise.rscsystem.service.RscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    @Resource
    private RscUserService rscUserService;

    @Autowired
    @Resource
    private MailService mailService;

    @Autowired
    @Resource
    private PostService postService;

    @RequestMapping(value = "/client")
    public String user(HttpSession session, Authentication authentication){
        //从登录中获取条件再从数据库中获取
        RscUser rscUser =rscUserService.findUserByName(authentication.getName());
        session.setAttribute("client", rscUser);
        return "client/client";
    }

    @RequestMapping(value = "/send")
    public String send(){
        return "client/send";
    }
    //@PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/sendSuccess")
    public String sendSuccess(@ModelAttribute Mail mail, HttpSession httpSession, Authentication authentication){
        //处理新邮件登记
        String address = mail.getReceive_address();
        mail.setMail_name(mail.getMail_name());
        mail.setSender_name(mail.getSender_name());
        mail.setReceiver_name(mail.getReceiver_name());
        mail.setReceive_address(mail.getReceive_address());
        mail.setReceiver_phone(mail.getReceiver_phone());
        mail.setSender_account(authentication.getName());
        mail.setMail_state("准备收件");
        //httpSession.setAttribute("mail", mail);//可以在下一个页面中使用sessionScope.mail.mail_name获取当前值
        //自动配备邮差
        Iterable<Postman> postmans = postService.postmanAssign();
        Iterator<Postman> p = postmans.iterator();
        //迭代器用于while循环遍历Iterable，并判断地址和请假情况,注意格式
        while (p.hasNext()){
            Postman postman = p.next();
            if (postman.getPostman_area().equals(address) && postman.getLeave() == 0 && postman.getActive().equals("1")){
                mail.setPostman_name(postman.getPostman_account());
                break;
            }
        }
        mailService.clientSend(mail);
        return "success";
    }
    @RequestMapping(value = "/mailState")
    public String mailState(HttpSession httpSession, Authentication authentication){
        Iterable<Mail> mails = mailService.findAllMailByAccount(authentication.getName());
        httpSession.setAttribute("mails", mails);

        Iterable<Mail> mailIterable = mailService.findAllByReceivername(authentication.getName());
        httpSession.setAttribute("getmails", mailIterable);
        return "client/mail_state";
    }
}
