package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Project_skill implements Serializable {
    @Id
    @Column(name = "pid")
    private int pid;

    @Column(name="skills")
    private String skills;

    public Project_skill(){

    }

    public Project_skill(int pid,String skills){
        this.pid = pid;
        this.skills = skills;
    }

    public int getPid() {
        return pid;
    }

    public String getSkills() {
        return skills;
    }
}
