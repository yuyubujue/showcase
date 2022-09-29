package com.eureka.ProjectDto;

public class ProjectDto {
    public String PID;
    public String PNAME;
    public String PLANGUAGE;
    public String PTEAMID;
    public String PUPLOADDATE;

    public ProjectDto() {

    }

    public ProjectDto(String PID,String PNAME,String PLANGUAGE,String PTEAMID,String PUPLOADDATE){
        this.PID = PID;
        this.PNAME = PNAME;
        this.PLANGUAGE = PLANGUAGE;
        this.PTEAMID = PTEAMID;
        this.PUPLOADDATE = PUPLOADDATE;
    }
}
