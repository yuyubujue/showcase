package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Project_comment implements Serializable {
    @Id
    @Column(name = "CID")
    private int cid;
    @Column(name = "PID")
    private int pid;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "TIME")
    private String time;
    @Column(name = "UID")
    private String uid;
    @Column(name = "UNAME")
    private String uname;


    public Project_comment(){

    }
    public Project_comment(int pid,String comment,String uid,String time,int cid,String uname){
        this.pid = pid;
        this.comment = comment;
        this.uid = uid;
        this.time = time;
        this.cid = cid;
        this.uname = uname;
    }

    public int getPid() {
        return pid;
    }

    public String getComment() {
        return comment;
    }

    public String getTime() {
        return time;
    }

    public String getUid() {
        return uid;
    }

    public int getCid() {
        return cid;
    }

    public String getUname() {
        return uname;
    }
}
