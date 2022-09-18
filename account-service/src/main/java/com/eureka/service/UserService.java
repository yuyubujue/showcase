package com.eureka.service;

import com.eureka.domain.User;
import com.eureka.dto.UserDTO;
import com.eureka.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService{
    @Autowired(required=false)
    private UserMapper userMapper;

    public int register(String userid, String username, String password, String email, String authority, String img) {
        return userMapper.insertUser(userid, username, password, email, authority, img);
    }

    public User login(String username,String password) {
        return userMapper.login(username,password);
    }

    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    public User findUserByName(String username){
        return userMapper.findUserByName(username);
    }

    public int updateCookie(String username, String cookie){
        return userMapper.updateCookie(username, cookie);
    }

    public User findUserByCookie(String cookie){
        return userMapper.findUserByCookie(cookie);
    }

    public List<UserDTO> getUsers(){
        return userMapper.getUsers();
    }

    public int setAuthority(String username, String authority){
        return userMapper.setAuthority(username, authority);
    }

    public int setPassword(String cookie, String password){
        return userMapper.setPassword(cookie, password);
    }

    public UserDTO findUser(String ID){
        return userMapper.findUser(ID);
    }

    public int setImg(String cookie, String img){
        return userMapper.setImg(cookie, img);
    }

    public String getImg(String id){
        return userMapper.getImg(id);
    }

    public int setIntroduction(String cookie, String introduction){return userMapper.setIntroduction(cookie, introduction);}
}