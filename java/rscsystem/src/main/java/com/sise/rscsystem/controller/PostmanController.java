package com.sise.rscsystem.controller;

import com.sise.rscsystem.bean.Mail;
import com.sise.rscsystem.bean.Postman;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping(value = "/post")
public class PostmanController {
    @Autowired
    @Resource
    private PostService postService;

    @Autowired
    @Resource
    private RscUserService rscUserService;

    @Autowired
    @Resource
    private MailService mailService;

    //记得先判断状态，再进行
    @RequestMapping(value = "/post_home")
    public String user(HttpSession session, Authentication authentication){
        //从登录中获取条件再从数据库中获取
        Postman postman =postService.findPostmanByName(authentication.getName());
        System.out.println("postman.getLeave_time()======================================"+postman.getLeave_time());
        session.setAttribute("post", postman);
        return "post/post_home";
    }

    //带处理==================收件==========================
    @RequestMapping(value = "/post_receive")
    public String postReceive(HttpSession session, Authentication authentication){
        Iterable<Mail> mails = mailService.findAllByPostman_nameAndReceiveNull(authentication.getName());
        session.setAttribute("mails", mails);
        return "post/post_receive";
    }
    //收件成功
    @RequestMapping(value = "/receive_success")
    public String receiveSuccess(@ModelAttribute Mail mail, HttpSession session, Authentication authentication){
        //mail对象为上个页面传过来的对象
        String address = mail.getReceive_address();
        System.out.println("address============================"+address);
        Mail realMail = mailService.findByMail_id(mail.getMail_id());
        System.out.println("mail.getMail_id()==========================="+mail.getMail_id());
        realMail.setMail_state("收件成功，准备派件");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);
        realMail.setReceive_time(dateString);
        Iterable<Postman> postmans = postService.postmanAssign();
        Iterator<Postman> p = postmans.iterator();
        //迭代器用于while循环遍历Iterable，并判断地址和请假情况,注意格式
        while (p.hasNext()){
            Postman newPostman = p.next();
            if (newPostman.getPostman_area().equals(address) && newPostman.getLeave() == 0 && newPostman.getActive().equals("1")){
                System.out.println("newPostman==========================================="+newPostman.getPostman_account());
                realMail.setPostman_name(newPostman.getPostman_account());
                break;
            }
        }
        mailService.mailChange(realMail);

        Postman postman = postService.findPostmanByName(authentication.getName());
        postman.setReceive_number(postman.getReceive_number() + 1);
        postService.postmanChange(postman);
        System.out.println("success===========================================");

        Iterable<Mail> mails = mailService.findAllByPostman_nameAndReceiveNull(authentication.getName());
        session.setAttribute("mails", mails);
        return "post/post_receive";
    }
    //收件失败
    @RequestMapping(value = "/receive_fail")
    public String receiveFail(@ModelAttribute Mail mail, HttpSession session){
        Mail realMail = mailService.findByMail_id(mail.getMail_id());
        session.setAttribute("mails", realMail);
        return "post/fail_reason";//填写原因
    }
    @RequestMapping(value = "/fail_reason")
    public String failReason(@ModelAttribute Mail failMail, HttpSession session, Authentication authentication){
        //MailID找Mail放原因
        Mail mail =mailService.findByMail_id(failMail.getMail_id());
        mail.setMail_state("收件失败");

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);

        mail.setFail_time(dateString);
        mail.setFail_reason(failMail.getFail_reason());
        mailService.mailChange(mail);

        Postman postman = postService.findPostmanByName(mail.getPostman_name());
        System.out.println("postman.getFailnumber()=================================="+postman.getFailnumber());
        postman.setFailnumber(postman.getFailnumber() + 1);
        postService.postmanChange(postman);

        Iterable<Mail> mails = mailService.findAllByPostman_nameAndReceiveNull(authentication.getName());
        session.setAttribute("mails", mails);
        return "post/post_receive";

    }

    //带处理=================派件===========================
    @RequestMapping(value = "/post_send")
    public String postSend(HttpSession session, Authentication authentication){
        Iterable<Mail> mails = mailService.findAllBySendNullAndReceiveNotNull(authentication.getName());
        session.setAttribute("sendMails", mails);
        return "post/post_send";
    }
    @RequestMapping(value = "/start_send")
    public String startSend(@ModelAttribute Mail mail, HttpSession session, Authentication authentication){
        Mail newMail =mailService.findByMail_id(mail.getMail_id());
        newMail.setMail_state("正在派件（路上）");
        mailService.mailChange(newMail);

        Iterable<Mail> mails = mailService.findAllBySendNullAndReceiveNotNull(authentication.getName());
        session.setAttribute("sendMails", mails);
        return "post/post_send";
    }
    @RequestMapping(value = "/sendSuccess")
    public String sendSuccess(@ModelAttribute Mail mail, HttpSession session, Authentication authentication){
        Mail realMail = mailService.findByMail_id(mail.getMail_id());
        realMail.setMail_state("派件完成");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);
        realMail.setSend_time(dateString);
        mailService.mailChange(realMail);

        Postman postman = postService.findPostmanByName(authentication.getName());
        postman.setSend_number(postman.getSend_number() + 1);
        postService.postmanChange(postman);

        Iterable<Mail> mails = mailService.findAllBySendNullAndReceiveNotNull(authentication.getName());
        session.setAttribute("sendMails", mails);
        return "post/post_send";
    }
    @RequestMapping(value = "/sendFail")
    public String sendFail(@ModelAttribute Mail mail, HttpSession session, Authentication authentication){
        if (mail.getSendFailNum() < 3){
            Mail realMail = mailService.findByMail_id(mail.getMail_id());
            realMail.setSendFailNum(mail.getSendFailNum() + 1);
            mailService.mailChange(realMail);

            Iterable<Mail> mails = mailService.findAllBySendNullAndReceiveNotNull(authentication.getName());
            session.setAttribute("sendMails", mails);
            return "post/post_send";
        }else {
            Mail realMail = mailService.findByMail_id(mail.getMail_id());
            session.setAttribute("mails", realMail);
            return "post/send_fail";
        }
    }
    @RequestMapping(value = "/sendFailReason")
    public String sendFailReason(@ModelAttribute Mail failMail, HttpSession session, Authentication authentication){
        //MailID找Mail放原因
        Mail mail =mailService.findByMail_id(failMail.getMail_id());
        mail.setMail_state("派件失败");

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);

        mail.setFail_time(dateString);
        mail.setFail_reason(failMail.getFail_reason());
        mailService.mailChange(mail);

        Postman postman = postService.findPostmanByName(mail.getPostman_name());
        postman.setFailnumber(postman.getFailnumber() + 1);
        postService.postmanChange(postman);

        Iterable<Mail> mails = mailService.findAllBySendNullAndReceiveNotNull(authentication.getName());
        session.setAttribute("sendMails", mails);
        return "post/post_send";
    }
    @RequestMapping(value = "/leave")
    public String postmanLeave(HttpSession session, Authentication authentication){
        Postman newPostman =postService.findPostmanByName(authentication.getName());
        newPostman.setLeave(1);
        newPostman.setLeave_time(newPostman.getLeave_time() + 1);
        postService.postmanChange(newPostman);
        session.setAttribute("post", newPostman);
        return "post/post_home";
    }
    @RequestMapping(value = "/cancelLeave")
    public String cancelLeave(HttpSession session, Authentication authentication){
        Postman newPostman =postService.findPostmanByName(authentication.getName());
        newPostman.setLeave(0);
        postService.postmanChange(newPostman);
        session.setAttribute("post", newPostman);
        return "post/post_home";
    }

}
