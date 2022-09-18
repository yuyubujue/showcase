package com.eureka.dto;

public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String img;
    private String introduction;

    public UserDTO() {}

    public UserDTO(String id, String username, String email, String img, String introduction) {
        this.id = id;
        this.username = username;
        this.img = img;
        this.email = email;
        this.introduction = introduction;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getImg(){
        return img;
    }


    public String getEmail(){
        return email;
    }

    public String getIntroduction(){
        return introduction;
    }
}

