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
    @Column(name = "ID")
    private int id;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "COMMENT_UID")
    private String comment_uid;
    @Column(name = "COMMENT_TIME")
    private long comment_time;
    @Column(name = "COMMENT_ID")
    private int comment_id;
    public Project_comment(){

    }
    public Project_comment(int id,String comment,String comment_uid,long comment_time,int comment_id){
        this.id = id;
        this.comment = comment;
        this.comment_uid = comment_uid;
        this.comment_time = comment_time;
        this.comment_id = comment_id;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public long getComment_time() {
        return comment_time;
    }

    public String getComment_uid() {
        return comment_uid;
    }

    public int getComment_id() {
        return comment_id;
    }
}
