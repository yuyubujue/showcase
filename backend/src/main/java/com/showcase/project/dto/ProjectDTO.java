package com.showcase.project.dto;

public class ProjectDTO {
    private int id;
    private String pname;
    private String technologies;
    private String owner;
    private String timestap;
    public ProjectDTO(){}
    public ProjectDTO(int id,String pname,String technologies,String owner,String timestap){
        this.id = id;
        this.pname = pname;
        this.technologies = technologies;
        this.owner = owner;
        this.timestap = timestap;
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

    public String getTechnologies() {
        return technologies;
    }

    public String getTimestap() {
        return timestap;
    }
}
