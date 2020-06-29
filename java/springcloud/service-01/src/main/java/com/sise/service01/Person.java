package com.sise.service01;

public class Person {

    Integer personid;
    String pname;
    int page;
    String message;//保存请求的URL

    public Person(){}

    public Person(Integer i1, String i2, int i3){
        personid = i1;
        pname = i2;
        page = i3;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
