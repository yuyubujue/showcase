package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class teacher_award implements Serializable {
    @Id
    @Column(name = "pid")
    private int pid;
    @Column(name = "teacher_id")
    private String teacher_id;
    @Column(name = "teacher_commet")
    private String teacher_comment;
    @Column(name = "award")
    private int award;
    @Column(name = "teacher_name")
    private String teacher_name;
    public teacher_award(){

    }
    public teacher_award(int pid,String teacher_id,String teacher_comment,int award,String teacher_name){
        this.pid= pid;
        this.teacher_comment = teacher_comment;
        this.teacher_id = teacher_id;
        this.award = award;
        this.teacher_name = teacher_name;
    }

    public int getAward() {
        return award;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public int getPid() {
        return pid;
    }

    public String getTeacher_comment() {
        return teacher_comment;
    }

    public String getTeacher_name() {
        return teacher_name;
    }
}
