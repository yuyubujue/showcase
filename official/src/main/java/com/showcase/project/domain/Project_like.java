package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Project_like implements Serializable {
    @Id
    @Column(name = "PID")
    private int pid;

    @Column(name = "UID")
    private String uid;

    @Column(name="LIKEAMOUNT")
    private int like_amount;

    public Project_like(){}
    public Project_like(int pid,String uid,int like_amount){
        this.pid = pid;
        this.uid = uid;
        this.like_amount = like_amount;
    }

    public int getLike() {
        return like_amount;
    }

    public int getPid(){
        return pid;
    }

    public String getUid() {
        return uid;
    }
}
