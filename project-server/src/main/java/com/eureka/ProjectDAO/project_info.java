package com.eureka.ProjectDAO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class project_info implements Serializable {
    @Id
    @Column(name = "PID")
    private String pid;


    @Column(name = "PNAME")
    private String pname;


    @Column(name = "PLANGEAGE")
    private String planguage;


    @Column(name = "PTEAMID")
    private String pteamid;


    @Column(name = "PUPLOADDATE")
    private String puploaddate;

    public project_info(){}

    public project_info(String pid,String pname,String planguage,String pteamid,String puploaddate){
        this.pid = pid;
        this.pname = pname;
        this.planguage = planguage;
        this.pteamid = pteamid;
        this.puploaddate = puploaddate;
    }


}
