package com.sise.rscsystem.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "postman_workload")
public class PostmanWorkload implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pw_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pw_id;
//    @Column(name = "postman_account")
//    private String postmanaccount;
    private int receive_number;
    private  int send_number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "save_time")
    private String savetime;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Postman.class)
    @JoinColumn(name = "postmanAccount", referencedColumnName = "postman_account")
    private Postman postman;

    public Long getPw_id() {
        return pw_id;
    }

    public void setPw_id(Long pw_id) {
        this.pw_id = pw_id;
    }

    public Postman getPostman() {
        return postman;
    }

    public void setPostman(Postman postman) {
        this.postman = postman;
    }

    //    public String getPostman_account() {
//        return postmanaccount;
//    }
//
//    public void setPostman_account(String postman_account) {
//        this.postmanaccount = postman_account;
//    }

    public int getReceive_number() {
        return receive_number;
    }

    public void setReceive_number(int receive_number) {
        this.receive_number = receive_number;
    }

    public int getSend_number() {
        return send_number;
    }

    public void setSend_number(int send_number) {
        this.send_number = send_number;
    }

    public String getSave_time() {
        return savetime;
    }

    public void setSave_time(String save_time) {
        this.savetime = save_time;
    }
}
