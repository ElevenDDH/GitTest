package com.sise.rscsystem.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "postman_workstate")
public class PostmanWorkstate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "workstate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workstate_id;
    @Column(name = "postman_account")
    private String postmanaccount;
    private int worktime;
    private int leavetime;
    private int overtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "savetime")
    private String savetime;

    public Long getWorkstate_id() {
        return workstate_id;
    }

    public void setWorkstate_id(Long workstate_id) {
        this.workstate_id = workstate_id;
    }

    public String getPostman_account() {
        return postmanaccount;
    }

    public void setPostman_account(String postman_account) {
        this.postmanaccount = postman_account;
    }

    public int getWorktime() {
        return worktime;
    }

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }

    public int getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(int leavetime) {
        this.leavetime = leavetime;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public String getSavetime() {
        return savetime;
    }

    public void setSavetime(String savetime) {
        this.savetime = savetime;
    }
}
