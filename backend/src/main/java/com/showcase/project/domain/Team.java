package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Team implements Serializable {
    @Id
    @Column(name = "TID")
    private String tid;
    @Column(name = "OWNER")
    private int owner;
    @Column(name = "UID")
    private String uid;
    @Column(name = "PID")
    private int pid;
    @Column(name = "TNAME")
    private String tname;
    @Column(name = "UNAME")
    private String uname;

    public Team(){
    }

    public Team(String tid,int owner,String uid,int pid,String tname,String uname){
        this.tid=tid;
        this.owner=owner;
        this.uid=uid;
        this.pid=pid;
        this.tname=tname;
        this.uname = uname;
    }

    public int getPid() {
        return pid;
    }

    public String getUid() {
        return uid;
    }

    public int getOwner() {
        return owner;
    }

    public String getTid() {
            return tid;
    }

    public String getUname() {
        return uname;
    }

    public String getTname() {
        return tname;
    }
}
