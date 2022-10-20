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
    @Column(name = "PID")
    private int pid;
    @Column(name = "TEACHERID")
    private String teacherid;
    @Column(name = "TEACHERCOMMENT")
    private String teachercomment;
    @Column(name = "AWARD")
    private int award;
    @Column(name = "TEACHERNAME")
    private String teachername;
    public teacher_award(){

    }
    public teacher_award(int pid,String teacherid,String teachercomment,int award,String teachername){
        this.pid= pid;
        this.teachercomment = teachercomment;
        this.teacherid = teacherid;
        this.award = award;
        this.teachername = teachername;
    }

    public int getAward() {
        return award;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public int getPid() {
        return pid;
    }

    public String getTeacher_comment() {
        return teachercomment;
    }

    public String getTeacher_name() {
        return teachername;
    }
}
