package com.sise.rscsystem.controller;

import com.sise.rscsystem.bean.*;
import com.sise.rscsystem.service.MailService;
import com.sise.rscsystem.service.PostService;
import com.sise.rscsystem.service.RscUserService;
import com.sise.rscsystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value = "/back")
public class BackController {
    @Autowired
    @Resource
    MailService mailService;

    @Autowired
    @Resource
    private PostService postService;

    @Autowired
    @Resource
    private RscUserService rscUserService;

    @Autowired
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping(value = "/back_home")
    public String backHome(){
        return "back/back_home";
    }

    @RequestMapping(value = "/mail_manage")
    public String mailManage(HttpSession httpSession){
        Iterable<Mail> mailDatas = mailService.findAllMail();
        httpSession.setAttribute("mail", mailDatas);
        return "back/mail_manage";
    }
    @RequestMapping(value = "/reManage")
    public String reManage(@ModelAttribute Mail mail, HttpSession httpSession){
        Mail newmail = mailService.findByMail_id(mail.getMail_id());
        String address = newmail.getReceive_address();
        if (newmail.getReceive_time() == null){
            newmail.setMail_state("准备收件");
        }
        if (newmail.getSend_time() == null && newmail.getReceive_time() != null){
            newmail.setMail_state("收件成功，准备派件");
        }
        System.out.println("newmail==================================="+newmail.getPostman_name());

        Iterable<Postman> postmans = postService.postmanAssign();
        Iterator<Postman> p = postmans.iterator();
        //迭代器用于while循环遍历Iterable，并判断地址和请假情况,注意格式
        while (p.hasNext()){
            Postman newPostman = p.next();
            if (newPostman.getPostman_area().equals(address) && newPostman.getLeave() == 0
                    && newPostman.getActive().equals("1") && !newPostman.getPostman_account().equals(newmail.getPostman_name())){
                newmail.setPostman_name(newPostman.getPostman_account());
                break;
            }
        }
        //更新邮差
        mailService.mailChange(newmail);
        System.out.println("更新邮差成功==================================");
        Iterable<Mail> mailDatas = mailService.findAllMail();
        httpSession.setAttribute("mail", mailDatas);
        return "back/mail_manage";
    }

    @RequestMapping(value = "/mail_count")
    public String mailCount(@ModelAttribute DataForm dataForm, HttpSession httpSession){
        //日，月：-11-
        //currentTime = "-27";
        String currentTime = dataForm.getCurrentTime();
        String currentDay = dataForm.getCurrentDay();
        String currentMonth = dataForm.getCurrentMonth();

        int sendCount = 0, receiveCount = 0, failCount = 0;
        int sendCountDay = 0, receiveCountDay = 0, failCountDay = 0;
        int sendCountMonth = 0, receiveCountMonth = 0, failCountMonth = 0;
        //年月日
        Iterable<Mail> sendDatas = mailService.getMailBySendTimeDayLikeSpecification(currentTime + "-" + currentMonth + "-" + currentDay);
        sendCount = ((List<Mail>) sendDatas).size();
        Iterable<Mail> receiveDatas = mailService.getMailByReceiveTimeDayLikeSpecification(currentTime + "-" + currentMonth + "-" + currentDay);
        receiveCount = ((List<Mail>) receiveDatas).size();
        Iterable<Mail> failDatas = mailService.getMailByFailTimeLikeDaySpecification(currentTime + "-" + currentMonth + "-" + currentDay);
        failCount = ((List<Mail>) failDatas).size();

        //日
        Iterable<Mail> sendDatasDay = mailService.getMailBySendTimeDayLikeSpecification("-" + currentDay);
        sendCountDay = ((List<Mail>) sendDatasDay).size();
        Iterable<Mail> receiveDatasDay = mailService.getMailByReceiveTimeDayLikeSpecification("-" + currentDay);
        receiveCountDay = ((List<Mail>) receiveDatasDay).size();
        Iterable<Mail> failDatasDay = mailService.getMailByFailTimeLikeDaySpecification("-" + currentDay);
        failCountDay = ((List<Mail>) failDatasDay).size();

        //月
        Iterable<Mail> sendDatasMonth = mailService.getMailBySendTimeDayLikeSpecification("-" + currentMonth + "-");
        sendCountMonth = ((List<Mail>) sendDatasMonth).size();
        Iterable<Mail> receiveDatasMonth = mailService.getMailByReceiveTimeDayLikeSpecification("-" + currentMonth + "-");
        receiveCountMonth = ((List<Mail>) receiveDatasMonth).size();
        Iterable<Mail> failDatasMonth = mailService.getMailByFailTimeLikeDaySpecification("-" + currentMonth + "-");
        failCountMonth = ((List<Mail>) failDatasMonth).size();

        httpSession.setAttribute("sendCount", sendCount);
        httpSession.setAttribute("receiveCount", receiveCount);
        httpSession.setAttribute("failCount", failCount);

        httpSession.setAttribute("sendCountDay", sendCountDay);
        httpSession.setAttribute("receiveCountDay", receiveCountDay);
        httpSession.setAttribute("failCountDay", failCountDay);

        httpSession.setAttribute("sendCountMonth", sendCountMonth);
        httpSession.setAttribute("receiveCountMonth", receiveCountMonth);
        httpSession.setAttribute("failCountMonth", failCountMonth);
        return "back/mail_count";
    }

    @RequestMapping(value = "/post_check")
    public String checkPost(HttpSession httpSession){
        //处理
        Iterable<Postman> postmen = postService.checkAllPost();
        httpSession.setAttribute("postmen", postmen);
        return "back/post_check";
    }

    @RequestMapping(value = "/post_agree")
    public String agreePost(@ModelAttribute Postman postman, HttpSession httpSession){
        //System.out.println("postman.getPostman_id()=====================" + postman.getPostman_id());
        postman.setActive("1");
        postService.postmanChange(postman);
        String name = postman.getPostman_account();

        //后台审核通过再执行
        RscUser user = rscUserService.findUserByName(name);
        //同时更新user_role
        Long user_id = user.getId();
        UserRole userRole = new UserRole();
        userRole.setUser_id(user_id);
        userRole.setRole_id((long)2);
        userRoleService.insertRole(userRole);
        //重新找一遍刷新审核页面
        Iterable<Postman> postmen = postService.checkAllPost();
        httpSession.setAttribute("postmen", postmen);
        return "back/post_check";
    }

    @RequestMapping(value = "/post_refuse")
    public String refusePost(@ModelAttribute RscUser rscUser, @ModelAttribute Postman postman, HttpSession httpSession){
        System.out.println("postman_account=====================" + postman.getPostman_account());
        postService.removeOnePost(postman);

        RscUser user = rscUserService.findUserByName(postman.getPostman_account());
        rscUserService.removeOneUser(user);

        //重新找一遍刷新审核页面
        Iterable<Postman> postmen = postService.checkAllPost();
        httpSession.setAttribute("postmen", postmen);
        return "back/post_check";
    }
    //工资处理
    @RequestMapping(value = "/countWage")
    public String countWage(HttpSession httpSession){
        Iterable<Postman>  postmans = postService.checkAllPost();
        httpSession.setAttribute("postmans", postmans);
        return "back/count_wage";
    }
    @RequestMapping(value = "/countWageSuccess")
    public String countWageSuccess(@ModelAttribute Postman postman, HttpSession httpSession){
        Postman newPostman = postService.findPostmanByName(postman.getPostman_account());
        newPostman.setWage(3000 + newPostman.getReceive_number()*10 + newPostman.getSend_number()*10);
        postService.postmanChange(newPostman);

        Iterable<Postman>  postmans = postService.checkAllPost();
        httpSession.setAttribute("postmans", postmans);
        return "back/count_wage";
    }
    @RequestMapping(value = "/countAllWageSuccess")
    public String countAllWageSuccess(HttpSession httpSession){
        Iterable<Postman>  postmans = postService.checkAllPost();
        Iterator<Postman> p = postmans.iterator();
        //迭代器用于while循环遍历Iterable，并判断地址和请假情况,注意格式
        while (p.hasNext()){
            Postman newPostman = p.next();
            newPostman.setWage(3000 + newPostman.getReceive_number()*10 + newPostman.getSend_number()*10);
            postService.postmanChange(newPostman);
        }
        httpSession.setAttribute("postmans", postmans);
        return "back/count_wage";
    }

    //查询邮差工作量
    @RequestMapping(value = "/postWorkload")
    public String workload(){
        return "back/post_workload";
    }


    //查询邮差工作量（收派件计件数）
    @RequestMapping(value = "/inquirePostWorkload")
    public String inquirePostWorkload(@ModelAttribute DataForm dataForm, HttpServletRequest request, HttpSession session){
        String area=request.getParameter("area");
        String time=dataForm.getTime();
        int count = 0, maxSend = 0, maxReceive = 0;
        String[] names = new String[20];
        int[] receiveNum = new int[20];
        int[] sendNum = new int[20];

        //获取
        //Map<Integer,Integer> map=new HashMap<>();
        List<PostmanWorkload> postmanWorkloads = postService.findByPostman_PostmanareaAndSave_time(area, time);
        for (PostmanWorkload postmanWorkload:postmanWorkloads){
            //count计数,max记最大值
            count += 1;
//            names[count] = postmanWorkload.getPostman().getPostman_account();
//            receiveNum[count] = postmanWorkload.getReceive_number();
//            sendNum[count] = postmanWorkload.getSend_number();

           // map.put(postmanWorkload.getReceive_number(), postmanWorkload.getSend_number());
            session.setAttribute("name"+count, postmanWorkload.getPostman().getPostman_account());
            session.setAttribute("receiveNum"+count, postmanWorkload.getReceive_number());
            session.setAttribute("sendNum"+count, postmanWorkload.getSend_number());

            if (maxReceive < postmanWorkload.getReceive_number()){
                maxReceive = postmanWorkload.getReceive_number();
            }
            if (maxSend < postmanWorkload.getSend_number()){
                maxSend = postmanWorkload.getSend_number();
            }
        }

//        session.setAttribute("names", names);
//        session.setAttribute("receiveNum", receiveNum);
//        session.setAttribute("sendNum", sendNum);

        session.setAttribute("maxReceive", maxReceive);
        session.setAttribute("maxSend", maxSend);
        session.setAttribute("count",count);

        return "back/post_workload";
    }

    //查询邮差工作情况
    @RequestMapping(value = "/postState")
    public String postState(HttpSession httpSession){
        Iterable<Postman> postmen = postService.checkAllPost();
        httpSession.setAttribute("postmen", postmen);
        return "back/post_workstate";
    }
    @RequestMapping(value = "/findByPostState")
    public String findPostState(@ModelAttribute PostmanWorkstate loadPostmanWorkstate, HttpSession httpSession){
        String account = loadPostmanWorkstate.getPostman_account();
        String time = loadPostmanWorkstate.getSavetime();

        PostmanWorkstate postmanWorkstate = postService.findWorkstateByPostmanaccountAndSavetimeLike(account, time);
        httpSession.setAttribute("postmanWorkstate", postmanWorkstate);
        httpSession.setAttribute("month", time);

        System.out.println("back ok=======================================");
        Iterable<Postman> postmen = postService.checkAllPost();
        httpSession.setAttribute("postmen", postmen);
        return "back/post_workstate";
    }
}
