package com.sise.db.jpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="class")
public class ClassSchool {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    String cid;
    String cname;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Student.class,mappedBy = "classSchool")
    private Set<Student> students = new HashSet<>();

    public ClassSchool(){
    }
    public ClassSchool(String s, Set<Student> es){
        cname = s;
        students = es;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
