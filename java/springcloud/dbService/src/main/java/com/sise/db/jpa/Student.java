package com.sise.db.jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="student")
public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    String sid;
    String sname;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ClassSchool.class)
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    ClassSchool classSchool;
    int rchinese;
    int rmath;
    int renglish;

    public Student() {super();}
    public Student(String sid, String sname, int rchinese, int rmath, int renglish) {
        super();
        this.sid = sid;
        this.sname = sname;
        this.rchinese = rchinese;
        this.rmath = rmath;
        this.renglish = renglish;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getRchinese() {
        return rchinese;
    }

    public void setRchinese(int rchinese) {
        this.rchinese = rchinese;
    }

    public int getRmath() {
        return rmath;
    }

    public void setRmath(int rmath) {
        this.rmath = rmath;
    }

    public int getRenglish() {
        return renglish;
    }

    public void setRenglish(int renglish) {
        this.renglish = renglish;
    }
}
