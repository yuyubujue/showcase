package com.showcase.project.dto;

import javax.persistence.Column;

public class ProjectLikeCommentDTO{
    private int id;

    private String pname;

    private String tagline;

    private String introduction;

    private String owner;

    private String timestamp;

    private String coverImage;

    private String updatetime;

    private int likeamount;

    private int award;

    private int comment;

    private String username;

    public ProjectLikeCommentDTO(){}

    public ProjectLikeCommentDTO(String pname, String tagline, String introduction,String owner,String timestamp,String coverImage,String updatetime,int likeamount,int award,int comment,String username) {
        this.pname = pname;
        this.tagline = tagline;
        this.owner = owner;
        this.introduction = introduction;
        this.timestamp = timestamp;
        this.coverImage = coverImage;
        this.updatetime = updatetime;
        this.likeamount = likeamount;
        this.award = award;
        this.comment = comment;
        this.username = username;

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

    public String getCoverImage(){return coverImage;}

    public String getTimestamp() {
        return timestamp;
    }

    public int getComment() {
        return comment;
    }

    public int getAward() {
        return award;
    }

    public String getUsername() {
        return username;
    }

    public int getLikeamount() {
        return likeamount;
    }
}
