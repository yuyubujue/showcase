package com.showcase.project.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;
import java.io.*;

@Entity
@Table
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "COOKIE")
    private String cookie;

    @Column(name = "AUTHORITY")
    private String authority;

    @Column(name = "IMG")
    private byte[] img;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "SKILL")
    private String skill;

    @Column(name = "INTEREST")
    private String interest;

    public User() {}

    public User(String username, String password, String email, String skill, String interest) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = "user";
        this.skill = skill;
        this.interest = interest;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getCookie(){
        return cookie;
    }

    public void setCookie(String cookie){
        this.cookie = cookie;
    }

    public byte[] getImg(){
        return img;
    }

    public void setImg(byte[] img){
        this.img = img;
    }

    public String getEmail(){
        return email;
    }

    public String getAuthority(){
        return authority;
    }

    public String getIntroduction(){
        return introduction;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(username, user.username)
                .append(password, user.password)
                .isEquals();
    }
}