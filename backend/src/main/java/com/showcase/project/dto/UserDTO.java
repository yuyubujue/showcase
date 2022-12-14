package com.showcase.project.dto;

public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String introduction;
    private String skill;
    private String interest;
    private String authority;

    public UserDTO() {}

    public UserDTO(String id, String username, String email, String introduction, String skill, String interest, String authority) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.introduction = introduction;
        this.skill = skill;
        this.interest = interest;
        this.authority=authority;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getIntroduction(){
        return introduction;
    }

    public String getSkill(){
        return skill;
    }

    public String getInterest(){
        return interest;
    }

    public String getAuthority(){
        return authority;
    }
}

