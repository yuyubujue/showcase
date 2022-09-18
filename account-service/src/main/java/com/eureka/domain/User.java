package com.eureka.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;

@Entity
@Table
public class User {

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
    private String img;

    @Column(name = "INTRODUCTION")
    private String introduction;


    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.img = "null.png";
        this.authority = "user";
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

    public String getImg(){
        return img;
    }

    public void setImg(String imgPath){
        this.img = imgPath;
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