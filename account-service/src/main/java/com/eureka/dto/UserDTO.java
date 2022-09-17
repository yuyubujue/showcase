package com.eureka.dto;

public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String img;

    public UserDTO() {}

    public UserDTO(String id, String username, String password, String email, String img) {
        this.id = id;
        this.username = username;
        this.img = img;
        this.email = email;
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

    public void setImg(String imgPath){
        this.img = imgPath;
    }

    public String getEmail(){
        return email;
    }
}
