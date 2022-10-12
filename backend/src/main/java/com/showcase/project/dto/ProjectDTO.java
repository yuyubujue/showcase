package com.showcase.project.dto;

public class ProjectDTO {
    private int id;
    private String pname;
    private String owner;
    private String timestamp;
    private String updatetime;
    public ProjectDTO(){}
    public ProjectDTO(int id,String pname,String owner,String timestamp,String updatetime){
        this.id = id;
        this.pname = pname;
        this.owner = owner;
        this.timestamp = timestamp;
        this.updatetime = updatetime;
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getPname() {
        return pname;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUpdatetime() {
        return updatetime;
    }
}
