package com.showcase.project.dto;

public class likeDTO {
    private int id;
    private String pname;

    private String owner;
    private String timestamp;
    private String likeamount;
    public likeDTO(){}
    public likeDTO(int id,String pname,String owner,String timestamp,String likeamount){
        this.id = id;
        this.pname = pname;
        this.owner = owner;
        this.timestamp = timestamp;
        this.likeamount = likeamount;
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

    public String getLikeamount(){
        return likeamount;
}

}
