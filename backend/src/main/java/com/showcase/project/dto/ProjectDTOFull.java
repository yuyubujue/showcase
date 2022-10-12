package com.showcase.project.dto;

import javax.persistence.Column;

public class ProjectDTOFull {
    private int id;

    private String pname;

    private String tagline;

    private String introduction;

    private String owner;

    private String timestamp;

    private byte[] coverImage;

    private String updatetime;

    public ProjectDTOFull(){}

    public ProjectDTOFull(String pname, String tagline, String introduction,String owner,String timestamp,byte[] coverImage,String updatetime) {
        this.pname = pname;
        this.tagline = tagline;
        this.owner = owner;
        this.introduction = introduction;
        this.timestamp = timestamp;
        this.coverImage = coverImage;
        this.updatetime = updatetime;
    }

    public int getID() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public String getTagline() {
        return tagline;
    }

    public String getOwner() {
        return owner;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public byte[] getCoverImage(){return coverImage;}

    public String getTimestamp() {
        return timestamp;
    }
}
