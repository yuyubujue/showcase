package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Verification implements Serializable {
    @Id
    @Column(name = "UNAME")
    private String uname;
    @Column(name = "PID")
    private int pid;
    @Column(name = "VERCODE")
    private String vercode;
    @Column(name = "TID")
    private String tid;
    @Column(name="TNAME")
    private String tname;
    public Verification(){}
    public Verification(String uname,int pid,String vercode,String tid,String tname){
        this.pid=pid;
        this.vercode=vercode;
        this.uname=uname;
        this.tid = tid;
        this.tname = tname;
    }

    public int getPid() {
        return pid;
    }

    public String getUname() {
        return uname;
    }

    public String getVercode() {
        return vercode;
    }

    public String getTid() {
        return tid;
    }

    public String getTname() {
        return tname;
    }
}
