package com.showcase.project.dto;



public class awardDTO {
    private int id;
    private String pname;

    private String owner;
    private String timestamp;
    private String awardtimes;
    public awardDTO(){}
    public awardDTO(int id,String pname,String owner,String timestamp,String awardtimes){
        this.id = id;
        this.pname = pname;
        this.owner = owner;
        this.timestamp = timestamp;
        this.awardtimes = awardtimes;
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

    public String getAward() {
        return awardtimes;
    }
}
