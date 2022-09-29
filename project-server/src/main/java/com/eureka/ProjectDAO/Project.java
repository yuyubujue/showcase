package com.eureka.ProjectDAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Project {
    @Id
    @Column(name = "pid")
    private String pid;


    @Column(name = "pname")
    private String pname;


    @Column(name = "planguage")
    private String planguage;


    @Column(name = "pteamid")
    private String pteamid;


    @Column(name = "puploaddate")
    private String puploaddate;

    public Project(){}

    public Project(String pid,String pname,String planguage,String pteamid,String puploaddate){
        this.pid = pid;
        this.pname = pname;
        this.planguage = planguage;
        this.pteamid = pteamid;
        this.puploaddate = puploaddate;
    }


}
