package com.sise.rscsystem.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;

@Entity
@Table(name = "mail")
public class Mail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id")//用来标识实体类中属性与数据表中字段的对应关系
    private Long mailid;
    private String mail_name;
    private String sender_name;
    //@NotNull(message = "收件地址不能为空")
    private String receive_address;
    @Column(name = "receiver_name")
    private String receivername;
    //@NotNull(message = "收件人电话不能为空")
    @Digits(integer = 11, fraction = 0, message = "电话格式不对")
    private String receiver_phone;
    private String mail_state;
    //@Column(name="send_time")
    //@CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "send_time")
    private String sendtime;//派件时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String dispatch_time;//废弃
    //@Column(name="receive_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "receive_time")
    private String receivetime;//收件时间
    //@Column(name="fail_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "fail_time")
    private String failtime;//失败时间
    private String fail_reason;
    @Column(name = "postman_name")
    private String postmanname;
    @Column(name = "sender_account")
    private String senderaccount;
    private int sendFailNum;

    public Long getMail_id() {
        return mailid;
    }

    public void setMail_id(Long mail_id) {
        this.mailid = mail_id;
    }

    public String getMail_name() {
        return mail_name;
    }

    public void setMail_name(String mail_name) {
        this.mail_name = mail_name;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceive_address() {
        return receive_address;
    }

    public void setReceive_address(String receive_address) {
        this.receive_address = receive_address;
    }

    public String getReceiver_name() {
        return receivername;
    }

    public void setReceiver_name(String receivername) {
        this.receivername = receivername;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getMail_state() {
        return mail_state;
    }

    public void setMail_state(String mail_state) {
        this.mail_state = mail_state;
    }

    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }

    public String getPostman_name() {
        return postmanname;
    }

    public void setPostman_name(String postmanname) {
        this.postmanname = postmanname;
    }

    public String getSend_time() {
        return sendtime;
    }

    public void setSend_time(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getReceive_time() {
        return receivetime;
    }

    public void setReceive_time(String receivetime) {
        this.receivetime = receivetime;
    }

    public String getFail_time() {
        return failtime;
    }

    public void setFail_time(String fail_time) {
        this.failtime = fail_time;
    }

    public String getSender_account() {
        return senderaccount;
    }

    public void setSender_account(String senderaccount) {
        this.senderaccount = senderaccount;
    }

    public String getDispatch_time() {
        return dispatch_time;
    }

    public void setDispatch_time(String dispatch_time) {
        this.dispatch_time = dispatch_time;
    }

    public int getSendFailNum() {
        return sendFailNum;
    }

    public void setSendFailNum(int sendFailNum) {
        this.sendFailNum = sendFailNum;
    }
}
