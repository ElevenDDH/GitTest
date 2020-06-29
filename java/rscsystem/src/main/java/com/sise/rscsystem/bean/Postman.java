package com.sise.rscsystem.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "postman")
public class Postman implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "postman_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postman_id;
    @Column(name = "postman_account")
    private String postmanaccount;
    @NotNull
    @Column(name = "postman_area")
    private String postmanarea;
    @Column(name = "fail_number")
    private int failnumber;
    @Column(name = "receive_number")
    private int receivenumber;
    @Column(name = "send_number")
    private int sendnumber;
    private int wage;
    private int work_time;//工作天数
    private int leave_time;//请假天数
    private int overtime;//加班天数
    private String active;//审核状态，0不通，1通
    @Column(name = "leave_state")
    private int leaveState;//请假状态，0正常，1请假
    @OneToMany(fetch = FetchType.LAZY, targetEntity = PostmanWorkload.class, mappedBy = "postman")
    private Set<PostmanWorkload> postmanWorkloads = new HashSet<>();

    public Long getPostman_id() {
        return postman_id;
    }

    public void setPostman_id(Long postman_id) {
        this.postman_id = postman_id;
    }

    public String getPostman_account() {
        return postmanaccount;
    }

    public void setPostman_account(String postmanaccount) {
        this.postmanaccount = postmanaccount;
    }

    public String getPostman_area() {
        return postmanarea;
    }

    public void setPostman_area(String postman_area) {
        this.postmanarea = postman_area;
    }

    public int getFailnumber() {
        return failnumber;
    }

    public void setFailnumber(int failnumber) {
        this.failnumber = failnumber;
    }

    public int getReceive_number() {
        return receivenumber;
    }

    public void setReceive_number(int receivenumber) {
        this.receivenumber = receivenumber;
    }

    public int getSend_number() {
        return sendnumber;
    }

    public void setSend_number(int sendnumber) {
        this.sendnumber = sendnumber;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getWork_time() {
        return work_time;
    }

    public void setWork_time(int work_time) {
        this.work_time = work_time;
    }

    public int getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(int leave_time) {
        this.leave_time = leave_time;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getLeave() {
        return leaveState;
    }

    public void setLeave(int leaveState) {
        this.leaveState = leaveState;
    }
}
