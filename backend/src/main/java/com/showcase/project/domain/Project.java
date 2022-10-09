package com.showcase.project.domain;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

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

    @Column(name = "TECHNOLOGIES")
    private String technologies;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "TIMESTAP")
    private long timestamp;

    @Column(name = "COVERIMAGE")
    private byte[] coverImage;

    public Project() {}

    public Project(String pname, String tagline, String technologies, String introduction,String owner) {
        this.pname = pname;
        this.tagline = tagline;
        this.technologies = technologies;
        this.owner = owner;
        this.introduction = introduction;
        this.timestamp = System.currentTimeMillis();
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

    public String getTechnologies() {
        return technologies;
    }

    public String getOwner() {
        return owner;
    }

    public String getIntroduction() {
        return introduction;
    }

    public long getDate() {
        return timestamp;
    }

    public byte[] getCoverImage(){return coverImage;}

}