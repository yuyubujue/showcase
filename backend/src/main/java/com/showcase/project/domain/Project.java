package com.showcase.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Project implements Serializable {


    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "PNAME")
    private String pname;

    @Column(name = "TAGLINE")
    private String tagline;


    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "TIMESTAP")
    private String timestamp;

    @Column(name = "COVERIMAGE")
    private byte[] coverImage;
    @Column(name = "UPDATETIME")
    private String updatetime;

    public Project() {}

    public Project(String pname, String tagline, String introduction,String owner,String timestamp,byte[] coverImage,String updatetime) {
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